# Proposal: Workout Routine Builder & Session Tracking

## Why

The backend currently supports generic activity logging (`activities` table with type/duration/intensity), but has no model for structured training. Users cannot define reusable workouts, track which exercises they performed with which weights/reps, or review their training history. This change adds routine and session tracking so users can:

- Define named routines (e.g. "Upper A", "Lower Power") built from exercises.
- Manage a personal exercise catalog with body-part tagging.
- Start a routine as a workout session, record per-set results, and keep an immutable history.

## What Changes

- **New domain entities** (clean-architecture layers: `domain`, `application`, `infrastructure.persistence`, `api`):
  - `Exercise` — name + optional notes; belongs to a user; has one or more body-part tags.
  - `Routine` — name + type (`UPPER`, `LOWER`, `FULLBODY`); belongs to a user; contains ordered routine-exercise entries, each with a default set count.
  - `WorkoutSession` — an instance of a routine started at a time; has status (`IN_PROGRESS`, `COMPLETED`) and a completed timestamp.
  - `SetRecord` — one performed set: session, exercise, set number, reps, weight (kg), timestamp.
- **New API endpoints** under `/api` (JWT-secured, user-scoped):
  - `POST /api/exercises`, `GET /api/exercises` — create/list exercises.
  - `POST /api/routines`, `GET /api/routines`, `GET /api/routines/{id}`, `PUT /api/routines/{id}`, `DELETE /api/routines/{id}` — CRUD routines including their exercise entries.
  - `POST /api/sessions`, `GET /api/sessions`, `GET /api/sessions/{id}` — start a session from a routine (optionally overriding per-exercise sets), list/get session history.
  - `POST /api/sessions/{id}/sets`, `GET /api/sessions/{id}/sets` — record and list set records.
- **New Flyway migration** creating `exercises`, `routines`, `routine_exercises`, `workout_sessions`, `set_records` tables with FKs to `users`.
- **No changes** to existing `activities`/`progress` endpoints; existing security config extended only to cover the new paths under the same rules.

## Capability Impact

- New capability: `workout-routines` (routine + exercise + session tracking domain).
- Existing capabilities unaffected: `user-auth` (reused as-is), activity tracking untouched.

## Risks

- Enum values (`RoutineType`, `BodyPart`) stored as VARCHAR — new values later require additive migration only (no data loss).
- Session completeness is not enforced (a user may complete a session without logging sets) — accepted for v1.
