# Tasks: Migrate to Expo

## 1. Setup and Configuration

- [x] 1.1 Initialize Expo Bare Workflow project in the `mobile` directory and verify the project structure is created
- [x] 1.2 Configure `app.json` with Expo SDK 51 and essential plugins (`expo-build-properties`, `expo-dev-client`, `expo-splash-screen`) and verify the configuration is valid
- [x] 1.3 Set up `.env.development`, `.env.staging`, and `.env.production` files and verify environment variables are loaded
- [x] 1.4 Configure GitHub Environments for `development`, `staging`, and `production` and verify the environments are accessible

## 2. Dependency Management

- [x] 2.1 Install Expo SDK 51 and essential plugins and verify the installation succeeds
- [x] 2.2 Ensure all dependencies (React Native, Redux Toolkit, Axios, React Navigation) are compatible with Expo and verify no conflicts exist
- [x] 2.3 Test WebSocket compatibility with Expo and verify real-time updates work as expected
- [x] 2.4 Test TypeScript compatibility with Expo and verify the shared module works as expected

## 3. Migration

- [x] 3.1 Migrate the React Native UI components to the Expo project and verify the components render correctly
- [x] 3.2 Migrate state management (Redux Toolkit) to the Expo project and verify state updates work as expected
- [x] 3.3 Migrate API clients (Axios) to the Expo project and verify API requests succeed
- [x] 3.4 Migrate navigation (React Navigation) to the Expo project and verify navigation works as expected
- [x] 3.5 Migrate styling (StyleSheet API) to the Expo project and verify the UI adheres to the design system

## 4. Testing

- [x] 4.1 Write automated tests (Jest) for WebSocket compatibility and verify all tests pass
- [x] 4.2 Write automated tests (Jest) for TypeScript compatibility and verify all tests pass
- [x] 4.3 Write unit and integration tests for all UI components and verify all tests pass
- [x] 4.4 Test the shared module with Expo and verify all tests pass

## 5. Deployment

- [x] 5.1 Configure Expo EAS Build for Android and verify the configuration is valid
- [x] 5.2 Set up GitHub Actions to use Expo EAS Build for cloud builds and verify the pipeline runs successfully
- [x] 5.3 Test the deployment pipeline and verify the Android app builds successfully

## 6. Documentation

- [x] 6.1 Update OpenSpec artifacts (`proposal.md`, `specs/`, `design.md`, `tasks.md`) and verify all artifacts are coherent
- [x] 6.2 Update `README.md` to reflect the new Expo-based architecture and verify the documentation is accurate
- [x] 6.3 Update `ARCHITECTURE.md` to document the Expo migration and verify the documentation is accurate

## 7. Cleanup

- [x] 7.1 Archive the Kotlin UI in the `kotlin-ui-archive` branch and verify the branch is preserved
- [x] 7.2 Remove the Kotlin UI from the main codebase and verify the removal is complete

## Workflow follow-up

- Archive the change after the project's review requirements are satisfied.
- Verify the archived result.