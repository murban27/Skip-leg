# AGENTS.md — FitnessApp Monorepo

## Project Purpose
FitnessApp is a multi-platform application designed for both trainers and clients to track workouts, fitness activities, and progress. Trainers can manage clients, assign training plans, and track progress metrics. Future capabilities include calorie tracking, visual progress photos, and social activity sharing.

---

## Core Tooling & Workflow

### 1. CodeGraph (`use_codegraph`)
- Always use the `codegraph` skill for symbol navigation, dependency resolution, and reference tracing across the codebase.
- Do not rely solely on plain-text search (`grep`/`find`) when inspecting architecture or refactoring types.

### 2. OpenSpec (Spec-Driven Development)
All non-trivial feature implementations, database changes, and structural updates MUST follow the OpenSpec lifecycle:
- **Proposal & Design:** `/opsx:propose`
- **Execution & Implementation:** `/opsx:apply`
- **Contract Archival:** `/opsx:archive`

---

## Development & Execution Rules

### Phase 1: Proposal & Research (`/opsx:propose`)
1. **Symbol Mapping:** Before creating a proposal, inspect affected classes, interfaces, and shared modules using `codegraph`.
2. **Scope Boundaries:** Clearly define affected packages/modules in `proposal.md` (e.g., core domain vs. mobile UI vs. backend API).
3. **Contracts First:** Specify strict types, endpoints, or data models in `spec.md` before generating operational code.

### Phase 2: Implementation (`/opsx:apply`)
1. **Context Check:** Re-verify symbol graphs via `codegraph` prior to altering code files.
2. **Deterministic Changes:** Execute strictly what is defined in the approved `spec.md`. Avoid out-of-scope refactoring or unnecessary code rewrites.
3. **Reference Verification:** Confirm all imports, types, and cross-module calls resolve cleanly.
4. **Targeted Verification:** Run local Gradle checks or unit tests for the modified module only.

---

## Monorepo Architecture & Rules
- **Layer Separation:** Keep business domain logic isolated from platform UI implementations (Jetpack Compose / Android / iOS).
- **Gradle & Proxy Gotchas:**
  - Build scripts and dependencies are managed via **Gradle**.
  - Respect global proxy configs defined in `~/.gradle/gradle.properties`.
  - Use `./gradlew <task>` wrapper commands for consistency.
- **No Hallucinated Imports:** Verify every class or function symbol via CodeGraph before referencing it in implementation code.