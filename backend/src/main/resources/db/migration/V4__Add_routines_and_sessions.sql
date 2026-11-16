CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    name VARCHAR(120) NOT NULL,
    notes VARCHAR(1000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE exercise_body_parts (
    id BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,
    body_part VARCHAR(30) NOT NULL
);
CREATE TABLE routines (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    name VARCHAR(120) NOT NULL,
    routine_type VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE routine_exercises (
    id BIGSERIAL PRIMARY KEY,
    routine_id BIGINT NOT NULL REFERENCES routines(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL,
    position_index INT NOT NULL,
    default_sets INT NOT NULL
);
CREATE TABLE workout_sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    routine_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP
);
CREATE TABLE session_exercises (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE CASCADE,
    exercise_id BIGINT,
    exercise_name VARCHAR(120) NOT NULL,
    default_sets INT NOT NULL
);
CREATE TABLE set_records (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE CASCADE,
    exercise_id BIGINT,
    exercise_name VARCHAR(120),
    reps INT NOT NULL,
    weight DECIMAL(6,2) NOT NULL,
    performed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);