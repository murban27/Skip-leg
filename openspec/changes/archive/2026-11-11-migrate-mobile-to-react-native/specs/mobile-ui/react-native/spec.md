# Spec: React Native UI

## Overview
This spec defines the requirements for migrating the FitnessApp mobile UI from Kotlin (Jetpack Compose) to React Native with TypeScript.

## Requirements

### 1. React Native Implementation
- The mobile UI must be implemented using React Native and TypeScript.
- The UI must be responsive and support both Android and iOS platforms.
- The UI must adhere to the project's design system and theme.

### 2. Feature-Based Folder Structure
- The React Native project must be organized by feature (e.g., `features/auth`, `features/activities`).
- Each feature must include its own components, screens, and state management logic.

### 3. State Management
- Use Redux Toolkit with slices for state management.
- State must be immutable and updated using actions and reducers.
- Selectors must be used to derive data from the state.

### 4. API Client
- Use Axios for making HTTP requests to the backend.
- API clients must be modular and reusable.
- Error handling must be implemented for all API requests.

### 5. Styling
- Use the React Native StyleSheet API for styling.
- Styles must be modular and reusable.
- The UI must adhere to the project's design system and theme.

### 6. Testing
- Use Jest and React Testing Library for unit and integration tests.
- Use MSW (Mock Service Worker) for mocking API interactions.
- Test coverage must include all critical paths and edge cases.

### 7. Real-Time Updates
- Use WebSocket for real-time updates (e.g., live workout tracking).
- WebSocket payloads must use JSON format.
- Error handling must be implemented for WebSocket connections.

### 8. Navigation
- Use React Navigation for handling navigation between screens.
- Navigation must be type-safe and modular.

### 9. Integration with Backend
- The React Native UI must reuse existing backend APIs.
- No versioning or modifications to the backend APIs are required.

### 10. Shared Module
- The `shared` module must be rewritten in TypeScript to align with the React Native UI.
- The shared module must include common logic for activities, authentication, and other shared functionality.

## Acceptance Criteria
- The React Native UI must replicate the functionality of the existing Kotlin UI.
- The UI must be responsive and support both Android and iOS platforms.
- All critical paths must be tested and covered by unit and integration tests.
- The UI must integrate seamlessly with the existing backend APIs.
- The `shared` module must be rewritten in TypeScript and integrated with the React Native UI.