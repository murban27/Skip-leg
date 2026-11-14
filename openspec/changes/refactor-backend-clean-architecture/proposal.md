# Proposal

## Why

The backend is neither compilable nor runnable: it has no `@SpringBootApplication` main class, no `application.yml`, uses `javax.servlet` against Spring Boot 3 (Jakarta), places classes in directories that do not match their packages, contains uncompilable test sources, and has a Dockerfile whose base image lacks Maven. Architecturally, all logic lives in fat controllers backed by a static in-memory list, with no domain, use-case, or persistence layers — violating the Clean Architecture structure this monorepo commits to (see `AGENTS.md` and the `clean-architecture` skill).

## What Changes

- Restructure `backend/` into Clean Architecture layers as packages of a single Maven module:
  - `fitnessapp.domain` — entities (`User`, `Activity`) and repository ports
  - `fitnessapp.application` — use cases (`RegisterUser`, `LoginUser`, `LogActivity`, `ListActivities`, `UpdateActivity`, `DeleteActivity`) and port interfaces
  - `fitnessapp.infrastructure` — JPA persistence adapters, Flyway, JWT token filter, WebSocket config, security config
  - `fitnessapp.api` — REST/STOMP controllers and DTOs serving the React Native mobile app
  - `fitnessapp.config` — composition root (bean wiring)
- Restore runnability:
  - Add `FitnessAppApplication` main class
  - Add `application.yml` (datasource, JPA, Flyway, WebSocket) receiving the `SPRING_DATASOURCE_*` env vars already passed by `docker-compose.yml`
  - Fix `javax.servlet` → `jakarta.servlet`; fix directory/package mismatches
  - Complete `pom.xml`: Flyway, Spring Security, JJWT (token parsing), validation, H2 (test scope)
  - Fix Dockerfile to a multi-stage build (Maven builder stage + JRE runtime stage)
- Persist activities via Spring Data JPA repositories backed by the existing V1–V3 Flyway migrations (replacing the static in-controller list) — **BREAKING** for anything relying on restart-wiped in-memory data: activities now survive restarts
- Keep mock authentication (mock register/login, `Bearer mock-jwt-token`) behind an `AuthUseCase` port so real auth can swap in at the composition root later
- Exempt `/api/auth/**` from the token filter (bug fix: today the filter blocks the login/register endpoints themselves) and protect all other `/api/**` routes
- Preserve the existing API contract exactly as documented in `backend/src/main/resources/static/docs/api-docs.yaml` (auth, activities CRUD, WebSocket `/ws` → `/topic/workout-updates`); no new endpoints (progress endpoints deferred)
- Replace the uncompilable tests with: use-case unit tests against in-memory fake ports, `@WebMvcTest` controller slice tests, and one `@SpringBootTest` Flyway smoke test (H2)

## Capabilities

### New Capabilities
- `backend-runtime`: The backend boots as a Spring Boot application with externalized configuration, runs Flyway migrations at startup, and builds/deploys as a container.

### Modified Capabilities
- `user-auth`: Mock login/registration endpoints are reachable without a token; all other `/api/**` endpoints require the `Bearer mock-jwt-token` header. Mock auth logic moves behind a use-case port.
- `activity-tracking`: Activities are persisted to PostgreSQL via JPA (survive restarts) instead of an in-memory list; CRUD behavior and response shapes are otherwise unchanged.
- `database-schema`: Migrations V1–V3 are applied automatically by Flyway at application startup (previously the SQL files were orphaned).

## Impact

- **Code**: rewrite of `backend/src/main/java/**` and `backend/src/test/java/**`; `backend/pom.xml` and `backend/Dockerfile` modified; new `backend/src/main/resources/application.yml`
- **APIs**: no endpoint/path/payload changes; only the auth-filter exemption changes observable behavior (auth endpoints become callable without a token)
- **Dependencies**: adds `flyway-core`, `spring-boot-starter-security`, `jjwt`, `spring-boot-starter-validation`, `h2` (test)
- **Systems**: `docker-compose.yml` unchanged; backend container now actually builds and connects to PostgreSQL
- **Mobile**: React Native app unaffected; it consumes the same documented contract
- **Not affected**: `mobile/`, `shared/`, `iosApp/`, WebSocket topics/destinations
