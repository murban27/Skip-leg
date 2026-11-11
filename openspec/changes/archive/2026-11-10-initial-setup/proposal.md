# Proposal

## Why

The **FitnessApp** project aims to create a mobile-first application for trainers and clients to track workouts, fitness activities, and progress. This initial setup establishes the foundational architecture, tooling, and core capabilities required to build a scalable and maintainable system. The goal is to enable:

- **Mock Authentication**: Simulate user authentication for local development.
- **Activity Tracking**: Logging workouts, progress, and metrics.
- **Real-Time Updates**: Live tracking of workouts and progress sharing.
- **Database Integration**: Structured storage for user data, activities, and progress.

This change is critical to lay the groundwork for future features, including calorie tracking, visual progress photos, and social sharing.


## What Changes

- **Project Structure**: Set up a monorepo with backend (Spring Boot), mobile (Kotlin/Swift), and shared modules.
- **Mock Authentication**: Simulate JWT-based authentication for local development.
- **API Layer**: Define RESTful endpoints for activity tracking and progress updates.
- **Database Schema**: Design a PostgreSQL schema for users, activities, and progress tracking.
- **Real-Time Features**: Integrate WebSockets for live workout tracking and updates.
- **Tooling**: Configure Gradle for build management, CodeGraph for symbol navigation, and OpenSpec for spec-driven development.
- **Docker Compose**: Enable quick setup and validation for local development.


## Capabilities

### New Capabilities
- `activity-tracking`: Logging and retrieving workout activities, progress, and metrics.
- `real-time-updates`: Real-time communication for live workout tracking and progress sharing.
- `database-schema`: Structured storage for user profiles, activities, and progress data.


## Impact

- **Code**: New backend services (Spring Boot), mobile apps (Kotlin/Swift), and shared modules.
- **APIs**: RESTful endpoints for activity tracking and real-time updates.
- **Dependencies**: PostgreSQL for database, WebSocket for real-time communication, and mock JWT for local authentication.
- **Systems**: Integration with mobile platforms (Android/iOS) and backend services.