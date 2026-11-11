# Architecture

## Overview
FitnessApp is a multi-platform application with a **backend**, **mobile UI**, and **shared module**. The backend is implemented in Java using Spring Boot, while the mobile UI is implemented in React Native with TypeScript. The shared module contains common logic for activities, authentication, and real-time updates.

## Backend

### Technology Stack
- **Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Real-Time Updates**: WebSocket

### Components
- **Controllers**: Handle HTTP requests and responses.
  - `ActivityController`: Manages fitness activities.
  - `AuthController`: Handles user authentication.
  - `WorkoutWebSocketController`: Manages WebSocket connections for real-time updates.
- **Services**: Contain business logic.
- **Repositories**: Interact with the database.
- **Configuration**: WebSocket and security configurations.

## Mobile

### Technology Stack
- **Language**: TypeScript
- **Framework**: React Native
- **State Management**: Redux Toolkit
- **API Client**: Axios
- **Navigation**: React Navigation
- **Styling**: React Native StyleSheet API
- **Testing**: Jest + React Testing Library

### Components
- **Features**: Organized by feature (e.g., `auth`, `activities`).
  - `LoginScreen`: Handles user authentication.
  - `ActivityLoggingScreen`: Allows users to log fitness activities.
  - `ActivityRetrievalScreen`: Displays logged activities.
  - `RealTimeUpdatesScreen`: Displays real-time updates (e.g., live workout tracking).
- **State Management**: Redux Toolkit with slices for authentication and activities.
- **API Clients**: Axios for HTTP requests to the backend.
- **Navigation**: React Navigation for handling navigation between screens.
- **Styling**: React Native StyleSheet API for styling.

## Shared

### Technology Stack
- **Language**: TypeScript
- **Purpose**: Shared logic for activities, authentication, and real-time updates.

### Components
- `ActivityService`: Manages fitness activities.
- `AuthService`: Handles user authentication.

## Integration

### Backend APIs
- The React Native UI reuses existing backend APIs (e.g., `/api/activities`).
- WebSocket is used for real-time updates (e.g., live workout tracking).

### Shared Module
- The shared module is integrated into the React Native UI and backend.
- It contains common logic for activities, authentication, and real-time updates.

## OpenSpec

### CodeGraph

- **CodeGraph**: Use the `codegraph` skill for symbol navigation, dependency resolution, and reference tracing across the codebase. Do not rely solely on plain-text search (`grep`/`find`) when inspecting architecture or refactoring types.

This project uses OpenSpec for spec-driven development. OpenSpec artifacts are located in the `openspec/` directory and include:
- **Proposal**: High-level overview of the change.
- **Specs**: Technical requirements for the change.
- **Design**: Technical design for the change.
- **Tasks**: Implementation steps for the change.