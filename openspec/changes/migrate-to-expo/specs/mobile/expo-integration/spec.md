# Spec Delta

## Purpose

Migrates the FitnessApp mobile UI to Expo Bare Workflow to simplify dependency management, builds, and deployments while retaining full control over native code.

## ADDED Requirements

### Requirement: Initialize Expo Bare Workflow
The system SHALL initialize a new Expo project using the Bare Workflow.

#### Scenario: Expo project initialized
- **WHEN** the Expo Bare Workflow is initialized in the `mobile` directory
- **THEN** the project is configured with Expo SDK 51 and essential plugins

### Requirement: Configure Expo SDK 51
The system SHALL configure the Expo project to use SDK 51.

#### Scenario: SDK 51 configured
- **WHEN** the Expo project is initialized
- **THEN** the `app.json` file specifies `sdkVersion: "51.0.0"`

### Requirement: Install Essential Plugins
The system SHALL install essential Expo plugins (`expo-build-properties`, `expo-dev-client`, `expo-splash-screen`).

#### Scenario: Essential plugins installed
- **WHEN** the Expo project dependencies are installed
- **THEN** the `package.json` includes `expo-build-properties`, `expo-dev-client`, and `expo-splash-screen`

### Requirement: Ensure Backend API Compatibility
The system SHALL ensure compatibility with existing backend APIs (e.g., `/api/activities`, WebSocket).

#### Scenario: Backend APIs compatible
- **WHEN** the Expo app makes requests to the backend APIs
- **THEN** the requests succeed and return the expected data

### Requirement: Ensure Shared Module Compatibility
The system SHALL ensure compatibility with the shared module (TypeScript).

#### Scenario: Shared module compatible
- **WHEN** the Expo app imports the shared module
- **THEN** the shared module functions work as expected