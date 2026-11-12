# Spec Delta

## Purpose

Uses Expo Application Services (EAS) for cloud builds and deployments to simplify the CI/CD pipeline.

## ADDED Requirements

### Requirement: Configure Expo EAS
The system SHALL configure Expo EAS for cloud builds.

#### Scenario: Expo EAS configured
- **WHEN** the Expo project is initialized
- **THEN** the `eas.json` file is created with configurations for Android builds

### Requirement: Set Up Android Builds
The system SHALL set up Expo EAS for Android builds.

#### Scenario: Android builds configured
- **WHEN** the `eas.json` file is created
- **THEN** the file includes a configuration for Android builds

### Requirement: Integrate with GitHub Actions
The system SHALL integrate Expo EAS with GitHub Actions for CI/CD.

#### Scenario: GitHub Actions integrated
- **WHEN** the GitHub Actions pipeline runs
- **THEN** the pipeline uses Expo EAS to build the Android app

### Requirement: Test Deployment Pipeline
The system SHALL test the deployment pipeline with Expo EAS.

#### Scenario: Deployment pipeline tested
- **WHEN** the GitHub Actions pipeline runs
- **THEN** the Android app builds successfully using Expo EAS