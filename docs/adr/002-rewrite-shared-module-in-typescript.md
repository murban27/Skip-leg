# ADR 002: Rewrite Shared Module in TypeScript

## Status
Accepted

## Context
The `shared` module was initially implemented in Kotlin to support both the backend and mobile UI. To align with the React Native UI and improve consistency, we decided to rewrite the `shared` module in TypeScript.

## Decision
Rewrite the `shared` module in TypeScript with the following components:
- `ActivityService`: Manages fitness activities.
- `AuthService`: Handles user authentication.

## Consequences
- **Pros**:
  - Ensures consistency with the React Native UI.
  - Simplifies integration with the React Native project.
  - Leverages TypeScript for type safety and developer productivity.
- **Cons**:
  - Requires rewriting the existing Kotlin logic.
  - Introduces a learning curve for developers unfamiliar with TypeScript.

## Alternatives Considered
- **Kotlin Multiplatform**: Offers shared logic but was not chosen due to limited integration with React Native.