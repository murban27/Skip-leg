# ADR 001: Adopt React Native for Mobile UI

## Status
Accepted

## Context
The FitnessApp mobile UI was initially implemented in Kotlin using Jetpack Compose. To align with the project's long-term goals of consolidating the codebase into a single technology stack and improving cross-platform maintainability, we decided to migrate the mobile UI to React Native with TypeScript.

## Decision
Adopt React Native for the mobile UI with the following tooling:
- **Language**: TypeScript
- **State Management**: Redux Toolkit
- **API Client**: Axios
- **Navigation**: React Navigation
- **Styling**: React Native StyleSheet API
- **Testing**: Jest + React Testing Library

## Consequences
- **Pros**:
  - Consolidates the mobile codebase into a single technology stack.
  - Improves cross-platform maintainability.
  - Leverages TypeScript for type safety and developer productivity.
- **Cons**:
  - Requires rewriting the existing Kotlin UI.
  - Introduces a learning curve for developers unfamiliar with React Native.

## Alternatives Considered
- **Flutter**: Offers cross-platform support but was not chosen due to the team's familiarity with React Native and TypeScript.
- **Kotlin Multiplatform**: Offers shared logic but was not chosen due to limited UI capabilities.