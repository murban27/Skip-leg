# Design: Workout Routines & Session Tracking

## Context

The backend follows a clean-architecture layout (`api` → `application` → `domain`, `infrastructure.persistence` for adapters) with Flyway-managed Postgres schema and JWT security. This change follows the same patterns as the existing `Activity` slice: controller → use case → repository port → JPA adapter.

## Goals / Non-Goals

- **Goals:** user-scoped routine CRUD, session start/complete, per-set history; schema evolution via Flyway; no breaking changes to existing endpoints.
- **Non-Goals:** trainer→client plan assignment, workout templating/marketplace, exercise media/images, progressive-overload analytics (later changes).

## Decisions

### D1: Schema (Flyway V4)

Single additive migration `V4__Add_routines_and_sessions.sql`:

```sql
CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(120) NOT NULL,
    notes VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE routines (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(120) NOT NULL,
    routine_type VARCHAR(20) NOT NULL,   -- UPPER / LOWER / FULLBODY
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE routine_exercises (
    id BIGSERIAL PRIMARY KEY,
    routine_id BIGINT NOT NULL REFERENCES routines(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,
    position INT NOT NULL,               -- ordering within the routine
    default_sets INT NOT NULL CHECK (default_sets > 0)
);

CREATE TABLE workout_sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    routine_id BIGINT NOT NULL REFERENCES routines(id),
    status VARCHAR(20) NOT NULL,         -- IN_PROGRESS / COMPLETED
    started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP
);

CREATE TABLE set_records (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id),
    reps INT NOT NULL CHECK (reps > 0),
    weight DECIMAL(6,2) NOT NULL CHECK (weight >= 0),
    performed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_set_records_session ON set_records(session_id);
CREATE INDEX idx_routine_exercises_routine ON routine_exercises(routine_id);
```

Rationale: `routine_exercises` is the join table that also carries per-routine `default_sets` (decision from proposal round: defaults live on the placement, not the exercise). `set_records` is append-only history; deleting a routine never deletes past session rows.

### D2: Enum handling

- `RoutineType` (`UPPER`, `LOWER`, `FULLBODY`) and `BodyPart` (`CHEST`, `BACK`, `SHOULDERS`, `BICEPS`, `TRICEPS`, `QUADS`, `HAMSTRINGS`, `GLUTES`, `CALVES`, `ABS`, `ARMS`, `LEGS`, `FULLBODY`, `CARDIO`) are Java enums persisted as VARCHAR via `@Enumerated(EnumType.STRING)`.
- Exercise↔body-part is a separate table `exercise_body_parts (exercise_id, body_part)` — no, folded into exercise tags via `@ElementCollection` on `ExerciseEntity` (table `exercise_body_parts`) to avoid a full entity for a pure tag. If a later change needs to query tags SQL-side, the table already exists.

### D3: API surface & DTO mapping

- Controllers in `api`: `ExerciseController`, `RoutineController`, `SessionController`.
- Requests/responses as records (`ExerciseRequest`, `RoutineRequest`, `SessionResponse`, …) with Jakarta validation; mapping to/from domain in the controller layer (consistent with existing `ActivityResponse` approach).
- Use cases in `application`: `StartSessionUseCase`, `CompleteSessionUseCase`, `CreateExerciseUseCase`, `CreateRoutineUseCase`, `ListRoutinesUseCase`, `GetRoutineUseCase` (etc.).
- Ports in `domain`: `ExerciseRepository`, `RoutineRepository`, `SessionRepository`; JPA adapters in `infrastructure.persistence`.

### D4: Ownership & authorization

All queries filter by the authenticated user's id (from JWT, same mechanism as existing `AuthController` usage). Cross-user ids return `404` (not `403`) to avoid resource enumeration. Enforced in use cases, not just controllers.

### D5: Session snapshot semantics

`workout_sessions` rows copy the resolved plan into `session_exercises` (session-scoped entry: exercise_id, default_sets captured at start). This keeps history stable even if the routine is later edited or deleted. `CompleteSessionUseCase` validates the session is not already completed (`409` otherwise).

## Risks / Trade-offs

- `[RBAC later]` No trainer/client sharing in v1 — sessions and routines are strictly personal.
- `[Migration]` V4 is additive only; rollback is a manual `DROP TABLE` set, acceptable pre-production.
- `[Perf]` No pagination on list endpoints in v1; add `Pageable` when datasets grow.

## Migration Plan

1. Ship V4 migration with the new tables.
2. Deploy code; existing endpoints untouched, so no coordinated deploy needed.

## Open Questions

- None blocking; open question from proposal (session completion enforcement) resolved as: completion is optional but explicit.
