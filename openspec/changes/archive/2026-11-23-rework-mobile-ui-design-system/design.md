# Design

## Context

`mobile/` is bare React Native 0.88 + TypeScript with Redux Toolkit and React Navigation (stack). Existing screens (Login, Activity Logging/Retrieval/Real-Time) are unstyled prototypes using raw RN controls and per-screen `StyleSheet`s; there is no theme module and no shared component library. `shared/` holds only `AuthService`/`ActivityService`. The app runs via `react-native run-android` against Metro; demo verification happens through Metro MCP on a running Android emulator. Styling guidance comes from the `expo-native-ui` skill's principles (structure/polish rules only — its Expo-specific APIs are not available in this bare-RN project).

## Goals / Non-Goals

**Goals:**
- One design token module + one primitives library that every current and future screen consumes, so new buttons/tables/cards inherit the brand automatically.
- A production-shaped workouts data model (`Workout`/`Exercise`) behind Redux, fed by mock data.
- Login → Workouts home as the post-login entry, visually demonstrating the design system.

**Non-Goals:**
- No Expo migration, no new UI dependencies (no NativeWind/Paper/Tamagui).
- No backend/API wiring, no workout editing or creation UI.
- No restyle of the three Activity screens (next round); no SwiftUI `iosApp/` changes.

## Decisions

1. **In-house tokens + primitives over a UI framework.** Zero dependencies, full control over the brand look, strongest guard against generic "framework template" styling. Alternatives (NativeWind, Paper, Tamagui) rejected: add dependency weight and impose a recognizable library aesthetic.
2. **Single branded dark theme, not platform-adaptive colors.** A fitness brand reads as one identity on both platforms; tokens are plain hex values in `theme/colors.ts`. Platform-native semantic colors (the `expo-native-ui` `Color` API) are unavailable in bare RN anyway.
3. **Theme layout**: `mobile/src/shared/theme/` with `colors.ts`, `typography.ts`, `spacing.ts`, `radii.ts`, `shadows.ts` and a barrel `index.ts`. Primitives in `mobile/src/shared/ui/`: `Screen.tsx` (safe-area + optional ScrollView wrapper), `AppText.tsx` (variant-based: title/heading/body/caption/numeric), `AppButton.tsx` (primary/secondary/ghost), `Card.tsx`, `AppTextInput.tsx`.
4. **Brand palette**: near-black background (`#0C0E0B` family), elevated card surface, off-white text, single volt/lime accent (~`#C8F135`) for primary actions and highlights; high-contrast secondary text via opacity, not gray literals. Typography: system font, bold large titles, `tabular-nums` for sets/reps/weight numbers.
5. **Workouts feature**: `mobile/src/features/workouts/` with `types.ts` (`Workout`, `Exercise`), `data/mockWorkouts.ts` (Upper / Lower / Full Body, each 4–6 exercises with sets × reps and some weights), `state/workoutsSlice.ts` (initial state = mock data; loader-shaped so an async thunk can replace it later), `components/WorkoutsHomeScreen.tsx`, `components/WorkoutCard.tsx`, `components/ExerciseRow.tsx`.
6. **Navigation**: add `WorkoutsHome` to the existing stack; after `login` dispatch succeeds, navigate (replace) to `WorkoutsHome`. Activity screens stay registered untouched.
7. **Styling practice** (from `expo-native-ui`, applied in bare RN): flex `gap` over margins, `contentContainerStyle` padding/gap on scrollables, safe-area insets via `react-native-safe-area-context` (already installed), `boxShadow` style prop, `borderCurve: 'continuous'`, `keyboardShouldPersistTaps="handled"` on the login form, every rendered control performs a real action.
8. **Delegation model (token-cost control)**: the orchestrator holds the full context and writes compact, file-scoped briefs; implementation is delegated to `@mobile-dev` (theme + primitives → workouts feature → login/nav wiring) and verification to `@test-engineer` (Jest + RNTL for slice, mock data, and component render tests). Each delegate receives only its brief + relevant file paths, not the whole conversation.

## Risks / Trade-offs

- [Custom primitives can drift back into per-screen styling] → Spec requires token/primitive usage; review checklist enforces "no raw color/spacing literals in screens".
- [Dark theme + single accent may reduce contrast on small text] → Typography tokens define minimum contrast pairs; secondary text uses opacity tiers of off-white, not mid-grays.
- [`boxShadow` on RN 0.88 Android] → RN 0.76+ supports CSS `boxShadow` on both platforms; verify on the emulator during the Metro MCP demo, fall back to a token-level substitution only if rendering fails.
- [Mock data may not match future backend shape] → Model mirrors a typical REST workout payload (ids, name, exercises[]); replacing the mock source is a slice-internal change.
- [Login currently dispatches without navigation logic] → Add navigation inside `LoginScreen` via auth state change or post-dispatch navigate; keep it mock-only per scope.

## Migration Plan

Single-round implementation on the RN app only; no data migration (mock data only). Rollback = revert the feature branch; no persisted state is introduced.

## Open Questions

None — all scope and visual decisions were settled during planning.
