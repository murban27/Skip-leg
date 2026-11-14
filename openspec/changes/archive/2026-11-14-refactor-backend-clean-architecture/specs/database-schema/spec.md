# Spec Delta

## ADDED Requirements

### Requirement: Migrations Applied Automatically
The system SHALL apply the `users`, `activities`, and `progress` migrations (V1–V3) automatically at application startup, in version order, before serving requests.

#### Scenario: Migration Order on Fresh Database
- **WHEN** the application starts against a fresh database
- **THEN** V1 (`users`), V2 (`activities`), and V3 (`progress`) are applied in order, and the application does not serve requests until they complete.

#### Scenario: Restart Skips Applied Migrations
- **WHEN** the application restarts against a database where V1–V3 were already applied
- **THEN** no migration runs a second time and no table is recreated or truncated.
