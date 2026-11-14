# Design: Migrate to Expo

## Context

The FitnessApp mobile UI currently uses React Native CLI, which has led to dependency conflicts, build complexities, and pipeline failures. The proposal ([proposal.md - Why](#)) outlines the need to migrate to Expo Bare Workflow to resolve these issues. This design document explains **how** to implement the migration.

## Goals / Non-Goals

**Goals:**
- Simplify dependency management, builds, and deployments using Expo Bare Workflow.
- Retain full control over native code for custom modules (e.g., WebSocket, backend integration).
- Ensure compatibility with existing backend APIs and the shared module.
- Maintain the existing testing strategy (Jest + React Testing Library).

**Non-Goals:**
- Migrate to Expo Managed Workflow (we are using Bare Workflow).
- Introduce iOS support initially (focus on Android only for now).
- Rewrite the backend or shared module logic (only ensure compatibility).

## Decisions

### 1. Expo Bare Workflow
**Decision**: Use Expo Bare Workflow instead of Expo Managed Workflow.
**Rationale**: Bare Workflow provides full control over native code while leveraging Expo’s tooling for builds and deployments. This aligns with the need to retain custom native modules (e.g., WebSocket, backend integration).
**Alternatives Considered**:
- **Expo Managed Workflow**: Simpler but lacks support for custom native modules.
- **React Native CLI**: Current approach, but leads to dependency conflicts and build complexities.

### 2. Expo SDK 51
**Decision**: Use Expo SDK 51 (latest stable).
**Rationale**: SDK 51 provides the latest features and improvements while maintaining stability. This ensures access to the newest Expo capabilities.
**Alternatives Considered**:
- **Expo SDK 50 (LTS)**: More stable but lacks the latest features.

### 3. Environment Configuration with `.env` Files
**Decision**: Use `.env` files and GitHub Environments for dynamic configurations.
**Rationale**: This approach provides flexibility and simplicity for managing environment-specific configurations (e.g., development, staging, production).
**Alternatives Considered**:
- **`app.config.js`**: Dynamic but more complex to maintain.
- **Hardcoded Configurations**: Not flexible or maintainable.

### 4. Expo EAS Build for Android Only
**Decision**: Use Expo EAS Build for Android only initially.
**Rationale**: This aligns with the goal of focusing on Android first. iOS support can be added later.
**Alternatives Considered**:
- **Expo EAS Build for Android and iOS**: Full cross-platform support but adds complexity.

### 5. Essential Expo Plugins
**Decision**: Use essential Expo plugins (`expo-build-properties`, `expo-dev-client`, `expo-splash-screen`).
**Rationale**: These plugins simplify native configurations and development workflows.
**Alternatives Considered**:
- **Manual Native Configurations**: More control but complex and error-prone.

### 6. Compatibility Testing
**Decision**: Test WebSocket and TypeScript compatibility with Expo using automated tests (Jest).
**Rationale**: Automated tests ensure reliability and catch issues early.
**Alternatives Considered**:
- **Manual Testing**: Simpler but not repeatable or reliable.

## Risks / Trade-offs

### Risks
1. **Dependency Conflicts**: Migrating to Expo may introduce new dependency conflicts.
   - **Mitigation**: Test dependencies thoroughly during the migration and use Expo’s compatibility tools.

2. **Build Failures**: Expo EAS Build may fail due to misconfigurations.
   - **Mitigation**: Test the build pipeline locally before deploying to CI/CD.

3. **Native Module Compatibility**: Custom native modules (e.g., WebSocket) may not be compatible with Expo.
   - **Mitigation**: Test native modules with Expo Bare Workflow and use Expo config plugins if needed.

4. **Testing Issues**: Jest and React Testing Library may not work as expected with Expo.
   - **Mitigation**: Test the testing framework with Expo and use Expo’s testing tools if needed.

### Trade-offs
1. **Expo Bare Workflow vs. React Native CLI**:
   - **Trade-off**: Expo Bare Workflow simplifies builds and deployments but introduces a dependency on Expo’s tooling.
   - **Mitigation**: Use Expo’s tooling for builds and deployments while retaining full control over native code.

2. **Expo SDK 51 vs. SDK 50**:
   - **Trade-off**: SDK 51 provides the latest features but may introduce instability.
   - **Mitigation**: Test thoroughly and monitor for breaking changes.

## Migration Plan

### Phase 1: Setup and Configuration
1. Initialize a new Expo project in the `mobile` directory using Expo Bare Workflow.
2. Configure `app.json` with Expo SDK 51 and essential plugins.
3. Set up `.env` files for environment-specific configurations.
4. Configure GitHub Environments for dynamic configurations.

### Phase 2: Dependency Management
1. Install Expo SDK 51 and essential plugins (`expo-build-properties`, `expo-dev-client`, `expo-splash-screen`).
2. Ensure all dependencies (e.g., React Native, Redux Toolkit, Axios) are compatible with Expo.
3. Resolve any dependency conflicts.

### Phase 3: Migration
1. Migrate the React Native UI components to the Expo project.
2. Ensure the shared module (TypeScript) is compatible with Expo.
3. Test WebSocket and TypeScript compatibility with Expo.

### Phase 4: Testing
1. Write automated tests (Jest) for WebSocket and TypeScript compatibility.
2. Test all UI components, state management, API clients, and navigation.
3. Ensure all tests pass.

### Phase 5: Deployment
1. Configure Expo EAS Build for Android.
2. Set up GitHub Actions to use Expo EAS Build for cloud builds.
3. Test the deployment pipeline.

### Phase 6: Documentation
1. Update OpenSpec artifacts (`proposal.md`, `specs/`, `design.md`, `tasks.md`).
2. Update `README.md` and `ARCHITECTURE.md` to reflect the new architecture.

### Rollback Strategy
If the migration fails or introduces critical issues:
1. Revert to the React Native CLI-based implementation.
2. Restore the previous `mobile` directory from the `kotlin-ui-archive` branch.
3. Investigate and address the issues before retrying the migration.

## Open Questions

None at this time. All critical decisions have been addressed in this design document.