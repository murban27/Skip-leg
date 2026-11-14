# Proposal: Migrate to Expo

## Why

The FitnessApp mobile UI currently uses React Native CLI, which has led to dependency conflicts, build complexities, and pipeline failures (e.g., Yarn version mismatches, Jest compatibility issues). Migrating to **Expo Bare Workflow** will simplify dependency management, builds, and deployments while retaining full control over native code. This change resolves the current pipeline issues and aligns with the goal of reducing maintenance overhead.

## What Changes

- **New Capabilities**:
  - **Expo Integration**: Migrate the React Native UI to Expo Bare Workflow for simplified builds, dependency management, and deployments.
  - **Expo EAS Build**: Use Expo Application Services (EAS) for cloud builds and deployments.
  - **Environment Configuration**: Use `.env` files and GitHub Environments for dynamic configurations.

- **Modified Capabilities**:
  - **Mobile UI**: Rewrite the React Native UI to be compatible with Expo Bare Workflow.
  - **Shared Module**: Ensure the shared module (TypeScript) is compatible with Expo.

- **Removals**:
  - **React Native CLI**: Replace React Native CLI with Expo Bare Workflow for builds and deployments.

## Capabilities

### New Capabilities
- `mobile/expo-integration`: Migrate the React Native UI to Expo Bare Workflow.
- `mobile/expo-eas-build`: Use Expo EAS for cloud builds and deployments.
- `mobile/environment-config`: Use `.env` files and GitHub Environments for dynamic configurations.

### Modified Capabilities
- `mobile-ui/react-native`: Rewrite the React Native UI to be compatible with Expo Bare Workflow.
- `shared/typescript`: Ensure the shared module is compatible with Expo.

## Impact

### Code
- **Mobile**: The React Native UI will be rewritten to use Expo Bare Workflow.
- **Shared**: The shared module will be updated to ensure compatibility with Expo.
- **Backend**: No changes to the backend APIs. The Expo-based UI will reuse existing endpoints.

### Dependencies
- **Expo SDK**: Introduce Expo SDK 51 and essential plugins (`expo-build-properties`, `expo-dev-client`, `expo-splash-screen`).
- **Yarn**: Continue using Yarn with Corepack for dependency management.
- **Testing**: Continue using Jest + React Testing Library for testing.

### Systems
- **CI/CD**: Update the GitHub Actions pipeline to use Expo EAS Build for cloud builds.
- **Deployment**: Use Expo EAS for deployments (Android only initially).
- **Documentation**: Update OpenSpec artifacts, `README.md`, and `ARCHITECTURE.md` to reflect the new architecture.