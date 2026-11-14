# Spec Delta

## MODIFIED Requirements

### Requirement: Mock User Login
The system SHALL simulate user login for local development by generating a mock JWT token, reachable without an Authorization header.

#### Scenario: Successful Mock Login
- **WHEN** a user requests a mock login
- **THEN** the system generates a mock JWT token for session management.

#### Scenario: Mock Token Validation
- **WHEN** a user submits a mock JWT token
- **THEN** the system validates the token and grants access to protected resources.

#### Scenario: Auth Endpoints Exempt From Token Requirement
- **WHEN** a request is made to any `/api/auth/**` endpoint without an Authorization header
- **THEN** the request is processed without a token check.

### Requirement: Mock User Registration
The system SHALL simulate user registration for local development, reachable without an Authorization header.

#### Scenario: Successful Mock Registration
- **WHEN** a user requests mock registration
- **THEN** the system creates a mock user account and returns a success response.

## ADDED Requirements

### Requirement: Protected API Routes
The system SHALL require a valid `Bearer mock-jwt-token` Authorization header for all `/api/**` routes outside `/api/auth/**`.

#### Scenario: Missing Token on Protected Route
- **WHEN** a request is made to a protected `/api/**` route without an Authorization header
- **THEN** the system responds with HTTP 401 Unauthorized.

#### Scenario: Invalid Token on Protected Route
- **WHEN** a request is made to a protected `/api/**` route with an Authorization header other than `Bearer mock-jwt-token`
- **THEN** the system responds with HTTP 401 Unauthorized.
