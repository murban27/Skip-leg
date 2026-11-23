# Proposal

## Why

The React Native app's screens are unstyled prototypes (raw `TextInput`/`Button`, hardcoded `StyleSheet`s, no shared theme), so every new screen drifts visually and the app reads as unfinished. A shared design system is needed now so that this UI rework and every future screen (buttons, tables, cards) inherit a consistent, modern look by construction instead of by copy-paste.

## What Changes

- Introduce an **in-house design token system** (`mobile/src/shared/theme/`): brand colors (dark-first, volt/lime accent), typography scale, spacing scale, radii, and shadows — no new UI dependencies.
- Introduce **shared UI primitives** (`mobile/src/shared/ui/`): `Screen`, `AppText`, `AppButton`, `Card`, `AppTextInput` built exclusively on the tokens, following the `expo-native-ui` styling principles (flex `gap`, `contentContainerStyle` padding, safe areas, `boxShadow` over `elevation`, `borderCurve: 'continuous'`).
- Add a **Workouts home screen** — the first screen after login — showing the user's saved workouts (e.g., Upper, Lower, Full Body) as cards, each listing its exercises (name, sets × reps, weight).
- Feed the home screen from a new **`workouts` Redux slice** with typed models (`Workout { id, name, exercises[] }`, `Exercise { id, name, sets, reps, weight? }`) backed by **mock data**; real backend wiring is a later change.
- Restyle the **Login screen** with the new primitives (mock auth: any email/password logs in) and navigate to the Workouts home on login.
- Change the post-login initial route to the Workouts home; the three Activity screens remain registered in the navigator unchanged (restyle deferred to a later change).
- Out of scope: SwiftUI `iosApp/`, backend, real API integration, workout editing/creation UI, the three Activity screens' restyle.

## Capabilities

### New Capabilities
- `mobile-ui/design-system`: The shared design token system and UI primitives that all mobile screens must use for colors, typography, spacing, and common controls; guarantees visual consistency as new screens and components are added.
- `mobile-ui/workouts-home`: The post-login home experience presenting the user's saved workouts and their exercises, initially served from mock data.

### Modified Capabilities

- None. Existing `mobile-ui/react-native` requirements (feature folders, Redux Toolkit, React Navigation, StyleSheet usage) remain satisfied; no requirement text changes.

## Impact

- **Code**: `mobile/src/shared/` (new theme + ui modules), `mobile/src/features/workouts/` (new: components, state, mock data), `mobile/src/features/auth/components/LoginScreen.tsx` (restyle), `mobile/src/navigation/AppNavigator.tsx` (new route, post-login entry).
- **Dependencies**: none added.
- **Systems**: no backend, API, or `shared/` module changes; SwiftUI `iosApp/` untouched.
- **Verification**: Jest + React Testing Library for new slice/components; visual approval via Metro MCP on a running Android emulator.
