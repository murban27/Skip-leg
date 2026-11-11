# Proposal: Migrate Mobile UI to React Native

## Why

The FitnessApp mobile UI is currently implemented in Kotlin using Jetpack Compose. To align with the project's long-term goals of consolidating the codebase into a single technology stack and improving cross-platform maintainability, we will migrate the mobile UI to React Native with TypeScript. This migration will:

- Simplify the codebase by removing Kotlin and consolidating the mobile UI into a single technology stack.
- Improve developer productivity by leveraging TypeScript and React Native best practices.
- Ensure consistency across the project by rewriting the `shared` module in TypeScript.
- Enable faster iteration and easier maintenance of the mobile UI.

## What Changes

### New Capabilities
- **React Native UI**: Replace the existing Kotlin UI with a React Native implementation.
- **TypeScript Adoption**: Enforce strict TypeScript for type safety and maintainability.
- **Feature-Based Folder Structure**: Organize the React Native project by feature (e.g., `features/auth`, `features/activities`).
- **State Management**: Use Redux Toolkit with slices for scalable state management.
- **API Client**: Use Axios for HTTP requests.
- **Styling**: Use the React Native StyleSheet API for styling.
- **Testing**: Use Jest + React Testing Library for unit and integration tests, with automated mocking tools (e.g., MSW) for API interactions.
- **Real-Time Updates**: Use WebSocket with JSON payloads for real-time updates.
- **Navigation**: Use React Navigation for handling navigation between screens.

### Modified Capabilities
- **Shared Module**: Rewrite the `shared` module in TypeScript to align with the React Native UI.
- **Backend Integration**: Reuse existing backend APIs without versioning.

### Removed Capabilities
- **Kotlin UI**: Remove the existing Kotlin UI implementation immediately after the React Native implementation is complete.

## Capabilities

### New Capabilities
- `mobile-ui/react-native`: Implement the mobile UI using React Native and TypeScript.
- `shared/typescript`: Rewrite the `shared` module in TypeScript to align with the React Native UI.

### Modified Capabilities
- `backend/integration`: Ensure seamless integration between the React Native UI and existing backend APIs.

## Impact

### Code
- **Mobile**: The entire Kotlin UI will be replaced with React Native components.
- **Shared**: The `shared` module will be rewritten in TypeScript.
- **Backend**: No changes to the backend APIs. The React Native UI will reuse existing endpoints.

### Dependencies
- **React Native**: Introduce React Native and its ecosystem (e.g., Redux Toolkit, Axios, React Navigation).
- **TypeScript**: Enforce strict TypeScript for type safety and maintainability.
- **Testing**: Introduce Jest, React Testing Library, and MSW for testing.

### Systems
- **CI/CD**: Update the GitHub Actions pipeline to support React Native builds and testing.
- **Documentation**: Update project documentation to reflect the new architecture and tooling.