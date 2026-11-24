# Workout Routines — Delta Spec

## ADDED Requirements

### Requirement: Exercise catalog
The system SHALL allow an authenticated user to create exercises and list their own exercises. Each exercise SHALL have a required unique-per-user name, an optional note, and one or more body-part tags.

#### Scenario: Create an exercise
- **WHEN** the user submits `POST /api/exercises` with `{ "name": "Bench Press", "bodyParts": ["CHEST", "TRICEPS"] }`
- **THEN** the response is `201` with the exercise JSON including its generated `id`, `name`, and `bodyParts` array.

#### Scenario: Reject exercise without body parts
- **WHEN** the user submits a create-exercise request with an empty `bodyParts` array
- **THEN** the response is `400` and no exercise is created.

#### Scenario: Body-part enum validation
- **WHEN** the user submits a create-exercise request with a `bodyParts` value that is not one of `CHEST, BACK, SHOULDERS, BICEPS, TRICEPS, QUADS, HAMSTRINGS, GLUTES, CALVES, ABS, ARMS, LEGS, FULLBODY, CARDIO`
- **THEN** the response is `400`.

#### Scenario: Exercises are user-scoped
- **WHEN** the user lists exercises via `GET /api/exercises`
- **THEN** only exercises created by that user are returned.

### Requirement: Routine management
The system SHALL allow an authenticated user to create, list, retrieve, update, and delete routines. A routine SHALL have a required name, a required type (`UPPER`, `LOWER`, or `FULLBODY`), and zero or more ordered exercise entries. Each entry SHALL reference an exercise and carry a default set count (minimum 1).

#### Scenario: Create a routine with exercises
- **WHEN** the user submits `POST /api/routines` with `{ "name": "Push Day", "type": "UPPER", "exercises": [{ "exerciseId": 1, "defaultSets": 4 }, { "exerciseId": 2, "defaultSets": 3 }] }`
- **THEN** the response is `201` with the routine JSON including its `id`, `name`, `type`, and the ordered `exercises` array with `defaultSets` per entry.

#### Scenario: Reject routine referencing a nonexistent exercise
- **WHEN** the user submits a create-routine request whose `exercises` array references an exercise id that does not exist or belongs to another user
- **THEN** the response is `400` (or `404`) and no routine is created.

#### Scenario: List routines is user-scoped
- **WHEN** the user lists routines via `GET /api/routines`
- **THEN** only routines owned by that user are returned.

#### Scenario: Update a routine
- **WHEN** the user submits `PUT /api/routines/{id}` with a new name, type, or exercise-entry list for a routine they own
- **THEN** the routine is replaced with the submitted definition and the updated routine JSON is returned.

#### Scenario: Delete a routine
- **WHEN** the user submits `DELETE /api/routines/{id}` for a routine they own
- **THEN** the routine and its routine-exercise entries are removed and the response is `204`. Past session records are NOT deleted.

### Requirement: Start a workout session
The system SHALL allow an authenticated user to start a session from an existing routine. The session SHALL snapshot the routine's exercise entries at start time, and the client MAY override the default set count per exercise for that session only, without modifying the routine.

#### Scenario: Start a session
- **WHEN** the user submits `POST /api/sessions` with `{ "routineId": 1 }`
- **THEN** the response is `201` with a session JSON containing `id`, `routineId`, `status: "IN_PROGRESS"`, `startedAt`, and the planned exercises (name + default sets).

#### Scenario: Start a session with overridden sets
- **WHEN** the user submits `POST /api/sessions` with `{ "routineId": 1, "overrides": [{ "exerciseId": 1, "sets": 5 }] }`
- **THEN** the session's planned entry for that exercise uses 5 sets while the routine definition itself remains unchanged.

#### Scenario: Session references another user's routine
- **WHEN** the user submits a start-session request whose `routineId` belongs to another user or does not exist
- **THEN** the response is `404` and no session is created.

### Requirement: Record set results
The system SHALL allow an authenticated user to record a performed set against a session they own, specifying the exercise, reps, and weight in kg. Each recorded set SHALL be persisted with its timestamp.

#### Scenario: Record a set
- **WHEN** the user submits `POST /api/sessions/{id}/sets` with `{ "exerciseId": 1, "reps": 8, "weight": 60.0 }`
- **THEN** the response is `201` with the set record JSON including `id`, `sessionId`, `exerciseId`, `reps`, `weight`, and `performedAt`.

#### Scenario: Reject sets on another user's session
- **WHEN** the user submits a set record for a session id they do not own
- **THEN** the response is `404` and no record is created.

#### Scenario: List recorded sets
- **WHEN** the user requests `GET /api/sessions/{id}/sets`
- **THEN** all set records for that session are returned ordered by creation order.

### Requirement: Session history
The system SHALL allow an authenticated user to list their past sessions and retrieve one session's detail, including its planned exercises and recorded sets. Completed sessions SHALL be immutable with respect to their started-at timestamp; a session MAY be explicitly completed via `POST /api/sessions/{id}/complete`.

#### Scenario: List session history
- **WHEN** the user requests `GET /api/sessions`
- **THEN** all of that user's sessions are returned, most recent first, each with `id`, `routineId`, `routineName`, `status`, and `startedAt`.

#### Scenario: Complete a session
- **WHEN** the user submits `POST /api/sessions/{id}/complete` for a session they own
- **THEN** the session's `status` becomes `COMPLETED`, its `completedAt` is set, and the session JSON is returned.

#### Scenario: Complete an already-completed session
- **WHEN** the user submits a complete request for a session already in `COMPLETED` status
- **THEN** the response is `409` and the status is unchanged.

### Requirement: Authentication on routine endpoints
All routine, exercise, and session endpoints SHALL require an authenticated user (JWT, consistent with existing `/api` endpoints). Requests without a valid token SHALL receive `401`.

#### Scenario: Unauthenticated access is rejected
- **WHEN** a request is made to any `/api/exercises`, `/api/routines`, or `/api/sessions` endpoint without a valid JWT
- **THEN** the response is `401` and no data is returned.
