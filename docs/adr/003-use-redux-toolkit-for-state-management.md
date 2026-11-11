# ADR 003: Use Redux Toolkit for State Management

## Status
Accepted

## Context
The React Native UI requires a scalable and maintainable solution for state management. We evaluated several options, including React Context, MobX, and Redux Toolkit.

## Decision
Use Redux Toolkit for state management with the following components:
- **Slices**: Organize state logic by feature (e.g., authentication, activities).
- **Selectors**: Derive data from the state.
- **Actions**: Update the state.

## Consequences
- **Pros**:
  - Provides a scalable and maintainable solution for state management.
  - Simplifies the creation of reducers, actions, and selectors.
  - Aligns with best practices for React Native applications.
- **Cons**:
  - Introduces additional boilerplate.
  - Requires a learning curve for developers unfamiliar with Redux.

## Alternatives Considered
- **React Context**: Lightweight but not scalable for complex state logic.
- **MobX**: Provides a reactive approach but was not chosen due to the team's familiarity with Redux.