# Design

## Context

The backend (`backend/`) is a Spring Boot 3.1.5 / Java 17 Maven module that does not compile or run: no main class, no `application.yml`, `javax.servlet` imports against Boot 3, package/directory mismatches, uncompilable test literals, and a Dockerfile whose base image lacks Maven. All logic lives in controllers; `ActivityController` stores state in a `static final List`. Flyway SQL (V1–V3) exists but is orphaned — no dependency, no datasource. The API contract is documented in `backend/src/main/resources/static/docs/api-docs.yaml` and consumed by the React Native app in `mobile/`. See proposal.md for the full problem statement.

## Goals / Non-Goals

**Goals:**
- Dependency rule enforced by package layout: `domain` ← `application` ← `infrastructure`/`api`, with `config` as the composition root
- Backend compiles, boots, migrates its schema, and runs under docker-compose against PostgreSQL
- Existing HTTP/WebSocket contract preserved byte-for-byte (paths, payloads, topics)
- Mock auth isolated behind a port so a real implementation swaps in at the composition root later

**Non-Goals:**
- Real authentication (BCrypt, signed JWTs) — deferred; mock stays behind the port
- Progress endpoints (`/api/progress`) — the table exists but the contract does not cover it yet
- Spring Boot version bump (3.1.5 is OSS-EOL; flagged as follow-up)
- Multi-module Maven build; any web UI; changes to `mobile/`, `shared/`, `iosApp/`

## Decisions

- **Single Maven module with packages, not multi-module.** ~6 endpoints don't justify pom scaffolding and slower builds; a future module split can follow the same package seams. *Alternative considered:* multi-module for compile-enforced dependency direction — rejected as premature.
- **Package layout**: `fitnessapp.domain` (entities + repository ports), `fitnessapp.application` (use cases + ports), `fitnessapp.infrastructure` (JPA adapters, Flyway, JWT filter, WebSocket/security config), `fitnessapp.api` (controllers + DTOs; named `api` rather than `presentation` because its only consumer is the React Native mobile app), `fitnessapp.config` (composition root bean wiring). Root package stays `fitnessapp` (matches groupId; renaming is churn).
- **Domain entities separate from JPA entities.** `domain.Activity` is a plain object; `infrastructure` holds `ActivityEntity` with an explicit mapper in the adapter. Boundary crossings use DTOs defined in `api`. *Alternative:* annotating domain entities with JPA — rejected; couples the domain to the framework.
- **Repository ports in domain, JPA interfaces in infrastructure.** Use cases depend on `ActivityRepository` (port); `JpaActivityRepository` (Spring Data) implements it, wrapped if mapping is needed. The in-memory fake used by unit tests lives in `src/test` — it is a test adapter, not production code.
- **Auth as a port.** `AuthUseCase` (application) implements register/login; the mock token rule stays in the use case, not in controllers. `JwtTokenFilter` (infrastructure) validates the `Authorization` header and is registered via Spring Security with `/api/auth/**` permitted and other `/api/**` authenticated. This fixes today's bug where the filter blocks login itself.
- **Flyway over `jpa.hibernate.ddl-auto`.** The V1–V3 SQL is the schema source of truth; Hibernate only validates (`ddl-auto: validate`). Migrations must stay H2-compatible for the smoke test (avoid PostgreSQL-only syntax in V1–V3; none is present today).
- **Dockerfile: multi-stage build.** Stage 1 `maven:3.9-eclipse-temurin-17` builds the JAR; stage 2 `eclipse-temurin:17-jre-jammy` runs it. Fixes the missing-Maven build failure and shrinks the runtime image.
- **Tests**: use-case unit tests with in-memory fakes (no Spring), `@WebMvcTest` slices with mocked use-case ports, one `@SpringBootTest` smoke test on H2 with Flyway enabled proving the context boots and migrations apply. Existing uncompilable tests are deleted, not patched — their assertions carry over.

## Risks / Trade-offs

- [H2 vs PostgreSQL dialect drift in the smoke test] → keep migrations to ANSI SQL (already true); CI/docker-compose exercises real PostgreSQL
- [Boot 3.1.5 is OSS-EOL] → pin now, bump in a dedicated follow-up change so this diff stays reviewable
- [Mock token in source is not real security] → accepted by decision; the port boundary ensures the swap point is a single adapter + config change
- [JPA mapping of `activities.user_id` FK requires a user row] → seed/mock flows must respect the FK; mock-register creates a user row so logged activities reference it
- [Static docs served from `static/docs` could drift from actual endpoints] → treat `api-docs.yaml` as the contract of record; contract tests in `@WebMvcTest` assert paths/payloads

## Migration Plan

All changes land in one PR on `refactor/backend-clean-architecture`. Deploy = rebuild the backend image (`docker compose up --build`); Flyway migrates on first boot. Rollback = redeploy previous image; data added under the new schema remains valid since V1–V3 are unchanged.

## Open Questions

None — all material decisions were settled with the user before this proposal.
