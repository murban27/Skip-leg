# database-schema Specification

## Purpose
Defines the structured storage for user profiles, activities, and progress data in PostgreSQL.

## Requirements

### Requirement: User Table
The system SHALL create a `users` table to store user profiles.

#### Scenario: User Table Creation
- **WHEN** the database schema is initialized
- **THEN** the system creates a `users` table with columns for `id`, `email`, `password_hash`, `role`, and `created_at`.

#### Scenario: User Table Constraints
- **WHEN** the `users` table is created
- **THEN** the system enforces uniqueness on the `email` column and non-null constraints on required fields.

### Requirement: Activity Table
The system SHALL create an `activities` table to store workout activities.

#### Scenario: Activity Table Creation
- **WHEN** the database schema is initialized
- **THEN** the system creates an `activities` table with columns for `id`, `user_id`, `type`, `duration`, `intensity`, and `created_at`.

#### Scenario: Activity Table Constraints
- **WHEN** the `activities` table is created
- **THEN** the system enforces a foreign key constraint on `user_id` referencing the `users` table.

### Requirement: Progress Table
The system SHALL create a `progress` table to store user progress metrics.

#### Scenario: Progress Table Creation
- **WHEN** the database schema is initialized
- **THEN** the system creates a `progress` table with columns for `id`, `user_id`, `metric_type`, `value`, and `recorded_at`.

#### Scenario: Progress Table Constraints
- **WHEN** the `progress` table is created
- **THEN** the system enforces a foreign key constraint on `user_id` referencing the `users` table.

### Requirement: Migrations Applied Automatically
The system SHALL apply the `users`, `activities`, and `progress` migrations (V1–V3) automatically at application startup, in version order, before serving requests.

#### Scenario: Migration Order on Fresh Database
- **WHEN** the application starts against a fresh database
- **THEN** V1 (`users`), V2 (`activities`), and V3 (`progress`) are applied in order, and the application does not serve requests until they complete.

#### Scenario: Restart Skips Applied Migrations
- **WHEN** the application restarts against a database where V1–V3 were already applied
- **THEN** no migration runs a second time and no table is recreated or truncated.
