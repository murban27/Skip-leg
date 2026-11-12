# Spec Delta

## Purpose

Uses `.env` files and GitHub Environments for dynamic configurations to support development, staging, and production environments.

## ADDED Requirements

### Requirement: Configure `.env` Files
The system SHALL use `.env` files for environment-specific configurations.

#### Scenario: `.env` files configured
- **WHEN** the Expo project is initialized
- **THEN** `.env.development`, `.env.staging`, and `.env.production` files are created

### Requirement: Set Up GitHub Environments
The system SHALL set up GitHub Environments for dynamic configurations.

#### Scenario: GitHub Environments configured
- **WHEN** the GitHub repository is configured
- **THEN** GitHub Environments for `development`, `staging`, and `production` are created

### Requirement: Load Environment Variables
The system SHALL load environment variables in the Expo app.

#### Scenario: Environment variables loaded
- **WHEN** the Expo app starts
- **THEN** the environment variables from the `.env` files are loaded

### Requirement: Use Environment Variables in App
The system SHALL use environment variables for API endpoints and other configurations.

#### Scenario: Environment variables used
- **WHEN** the Expo app makes requests to the backend APIs
- **THEN** the API endpoints are loaded from the environment variables