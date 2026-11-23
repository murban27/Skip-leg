# Design System Specification

## Purpose

Defines the shared design system for the React Native mobile UI: a single source of design tokens (color, typography, spacing, radii, shadows) and a set of reusable UI primitives. All screens and components MUST consume these tokens and primitives so the app stays visually consistent as new buttons, tables, cards, and screens are added.

## Requirements

### Requirement: Design Tokens Are the Single Styling Source
The mobile app SHALL expose a design token module as the single source of truth for colors, typography, spacing, border radii, and shadows.
- All screens and reusable components MUST reference these tokens instead of hardcoding raw style values.
- The token set MUST define a dark-first brand theme with a single energetic accent color, applied identically on Android and iOS.

#### Scenario: No Hardcoded Palette in Screens
- **WHEN** any screen or reusable component renders styled content,
- **THEN** its colors, spacing, and typography come from the design token module, not from literals defined in the screen.

#### Scenario: Consistent Theme Across Platforms
- **WHEN** the app runs on Android and on iOS,
- **THEN** both platforms render the same branded dark theme with the same accent color.

### Requirement: Shared UI Primitives
The app SHALL provide reusable UI primitives for screen scaffolding and common controls, built exclusively on the design tokens.
- The primitive set MUST at minimum include a screen container, a text component, a button, a card container, and a text input.
- Feature screens MUST use these primitives for scaffolding and common controls instead of re-creating raw React Native control styling per screen.

#### Scenario: New Control Inherits the Design
- **WHEN** a developer adds a new button, input, or card to any screen using the shared primitives,
- **THEN** it renders with the app's brand styling without defining any local style values.

#### Scenario: Login Screen Uses Primitives
- **WHEN** the login screen renders,
- **THEN** all of its text, inputs, and buttons are rendered through the shared primitives with the branded dark theme.

### Requirement: Modern Styling Rules
The app's styling SHALL follow modern React Native styling practices.
- Layouts MUST prefer flex `gap` over margins for spacing between siblings.
- Scrollable screen content MUST apply padding and gap via scroll content container styles.
- Shadows MUST use the box-shadow style prop, not legacy shadow or elevation props.
- Rounded corners MUST use continuous border curves except for capsule shapes.
- Screens with scrollable content MUST account for top and bottom safe area insets.

#### Scenario: Scrollable Home Screen Respects Safe Areas
- **WHEN** a scrollable screen is displayed on a device with display cutouts,
- **THEN** its content remains fully visible and inset from the top and bottom safe areas.

### Requirement: Post-Login Entry Screen
After a successful login, the app SHALL navigate to the workouts home screen as the first screen the user sees.
- Login SHALL succeed with any non-empty email and password (mock authentication) until real authentication wiring is added.

#### Scenario: Login Navigates to Workouts Home
- **WHEN** the user submits the login form with a non-empty email and password,
- **THEN** the app navigates to the workouts home screen as the active screen.
