# Spec Delta

## Purpose

The backend boots as a Spring Boot application with externalized configuration, applies database migrations automatically at startup, and builds and runs as a container alongside PostgreSQL.

## ADDED Requirements

### Requirement: Application Bootstrap
The system SHALL boot as a Spring Boot application from a single main class using Maven as the build tool.

#### Scenario: Application starts locally
- **WHEN** the application is started from the `backend` module (via Maven or the packaged JAR)
- **THEN** the application starts successfully and serves HTTP on port 8080.

### Requirement: Externalized Configuration
The system SHALL configure its datasource, JPA, and Flyway settings through `application.yml`, with values overridable by `SPRING_DATASOURCE_*` environment variables.

#### Scenario: Compose environment variables are honored
- **WHEN** the application runs with `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD` environment variables set
- **THEN** the application connects to the PostgreSQL database described by those variables.

### Requirement: Automatic Migration at Startup
The system SHALL apply all pending Flyway migrations (V1–V3) to the configured database before serving requests.

#### Scenario: Fresh database is migrated
- **WHEN** the application starts against a database with no tables
- **THEN** the `users`, `activities`, and `progress` tables exist after startup completes.

### Requirement: Container Build and Run
The system SHALL provide a Dockerfile that builds the backend with Maven inside the image and runs the packaged JAR.

#### Scenario: Docker image builds and starts
- **WHEN** `docker compose up --build` is run from the repository root
- **THEN** the backend image builds without host-installed Maven, the backend container starts, and it connects to the PostgreSQL container.
