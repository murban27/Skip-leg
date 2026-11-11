# Design: Migrate Mobile UI to React Native

## Overview
This document outlines the technical design for migrating the FitnessApp mobile UI from Kotlin (Jetpack Compose) to React Native with TypeScript.

## Architecture

### 1. Folder Structure
The React Native project will adopt a **feature-based folder structure** to ensure modularity and scalability:

```
/src
  /features
    /auth
      /components
      /screens
      /state
      /types
    /activities
      /components
      /screens
      /state
      /types
  /shared
    /api
    /state
    /types
  /navigation
  /theme
```

### 2. State Management
- **Redux Toolkit** will be used for state management.
- **Slices** will be used to organize state logic by feature.
- **Selectors** will be used to derive data from the state.

### 3. API Client
- **Axios** will be used for making HTTP requests to the backend.
- API clients will be modular and reusable.
- Error handling will be implemented for all API requests.

### 4. Styling
- The **React Native StyleSheet API** will be used for styling.
- Styles will be modular and reusable.
- The UI will adhere to the project's design system and theme.

### 5. Testing
- **Jest** and **React Testing Library** will be used for unit and integration tests.
- **MSW (Mock Service Worker)** will be used for mocking API interactions.
- Test coverage will include all critical paths and edge cases.

### 6. Real-Time Updates
- **WebSocket** will be used for real-time updates (e.g., live workout tracking).
- WebSocket payloads will use **JSON** format.
- Error handling will be implemented for WebSocket connections.

### 7. Navigation
- **React Navigation** will be used for handling navigation between screens.
- Navigation will be type-safe and modular.

### 8. Integration with Backend
- The React Native UI will reuse existing backend APIs.
- No versioning or modifications to the backend APIs are required.

### 9. Shared Module
- The `shared` module will be rewritten in TypeScript to align with the React Native UI.
- The shared module will include common logic for activities, authentication, and real-time updates.

## Implementation Steps

### 1. Setup React Native Project
- Initialize a new React Native project in the monorepo.
- Configure TypeScript with strict settings.
- Set up Redux Toolkit, Axios, React Navigation, and other dependencies.

### 2. Rewrite Shared Module
- Rewrite the `shared` module in TypeScript.
- Implement common logic for activities, authentication, and real-time updates.
- Integrate the shared module with the React Native UI.

### 3. Implement React Native UI
- Implement the UI components and screens using React Native.
- Organize the project by feature (e.g., `features/auth`, `features/activities`).
- Implement state management using Redux Toolkit.
- Implement API clients using Axios.
- Implement styling using the StyleSheet API.
- Implement navigation using React Navigation.

### 4. Testing
- Write unit and integration tests using Jest and React Testing Library.
- Mock API interactions using MSW.
- Test all critical paths and edge cases.

### 5. Integration
- Integrate the React Native UI with the existing backend APIs.
- Ensure seamless communication between the UI and backend.

### 6. Remove Kotlin UI
- Archive the Kotlin UI in a separate branch.
- Remove the Kotlin UI from the main codebase.

## ADRs
Architectural decisions will be documented as **Architecture Decision Records (ADRs)** in the `docs/adr` directory. Key decisions include:

- **ADR 1**: Adopt React Native for the mobile UI.
- **ADR 2**: Use Redux Toolkit for state management.
- **ADR 3**: Rewrite the `shared` module in TypeScript.
- **ADR 4**: Use WebSocket for real-time updates.
- **ADR 5**: Use GitHub Actions for CI/CD.