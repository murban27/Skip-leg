# Tasks: Workout Routines & Session Tracking

## 1. Persistence layer

- [x] 1.1 Add Flyway migration `V4__Add_routines_and_sessions.sql` creating `exercises`, `exercise_body_parts`, `routines`, `routine_exercises`, `workout_sessions`, `session_exercises`, `set_records` per design D1/D2.
- [x] 1.2 Add `ExerciseEntity` (with `@ElementCollection` body parts), `RoutineEntity`, `RoutineExerciseEntity`, `WorkoutSessionEntity`, `SessionExerciseEntity`, `SetRecordEntity` in `infrastructure.persistence`.
- [x] 1.3 Add Spring Data JPA interfaces (`ExerciseJpaRepository`, `RoutineJpaRepository`, `WorkoutSessionJpaRepository`, `SetRecordJpaRepository`) with user-scoped finder methods.
- [x] 1.4 Add domain ports `ExerciseRepository`, `RoutineRepository`, `SessionRepository` in `domain` and JPA adapter implementations in `infrastructure.persistence`.

## 2. Application layer (use cases)

- [x] 2.1 `CreateExerciseUseCase` — validate name/body parts, enforce ownership.
- [x] 2.2 `CreateRoutineUseCase` — validate type enum, exercise ownership, positions, default sets ≥ 1.
- [x] 2.3 `ListRoutinesUseCase`, `GetRoutineUseCase`, `UpdateRoutineUseCase`, `DeleteRoutineUseCase` — user-scoped.
- [x] 2.4 `StartSessionUseCase` — load routine, snapshot entries into `session_exercises`, apply per-exercise set overrides.
- [x] 2.5 `CompleteSessionUseCase` — transition `IN_PROGRESS → COMPLETED`, reject double completion (`409`).
- [x] 2.6 `RecordSetUseCase`, `ListSessionSetsUseCase`, `ListSessionsUseCase` — user-scoped, ordered history.

## 3. API layer

- [x] 3.1 `ExerciseController` — `POST /api/exercises`, `GET /api/exercises` with validation and DTO records.
- [x] 3.2 `RoutineController` — CRUD endpoints per spec, ordered `exercises` array in request/response DTOs.
- [x] 3.3 `SessionController` — `POST /api/sessions` (with optional overrides), `GET /api/sessions`, `GET /api/sessions/{id}`, `POST /api/sessions/{id}/complete`, `POST /api/sessions/{id}/sets`, `GET /api/sessions/{id}/sets`.
- [x] 3.4 Extend `SecurityConfig` so new `/api/exercises`, `/api/routines`, `/api/sessions` paths require authentication (same rule as existing `/api/**`).
- [x] 3.5 Map new exceptions (`RoutineNotFoundException`, `SessionNotFoundException`, `SessionAlreadyCompletedException`) in `ApiExceptionHandler`.

## 4. Tests

- [x] 4.1 Use-case unit tests: create exercise (valid/invalid enum/empty body parts), routine CRUD ownership, start session with and without overrides, complete session idempotency rejection, record set on foreign session → 404.
- [x] 4.2 Controller tests (`@WebMvcTest` or existing test pattern): happy paths + `401` unauthenticated + `400` validation for each new endpoint.
- [x] 4.3 Flyway migration test: context loads against H2 with `ddl-auto: validate` and V1–V4 applied.

## 5. Verification & docs

- [x] 5.1 `cd backend && mvn test` — all existing (17) and new tests green.
- [x] 5.2 Update `backend/src/main/resources/static/docs/api-docs.yaml` with the new endpoints.
- [x] 5.3 Commit work on the `workout-routines` worktree branch with conventional messages per task group.
