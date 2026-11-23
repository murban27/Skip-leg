# Workouts Home Specification

## Purpose

Defines the post-login home experience: the user's saved workouts presented as cards listing each workout's exercises. Workouts are served from mock data until backend integration is added, but the data model must be production-shaped so wiring a real API later does not change the UI.

## Requirements

### Requirement: Saved Workouts Overview
The workouts home screen SHALL display all of the user's saved workouts as a scrollable list of workout cards.
- Each card MUST show the workout's name (e.g., Upper, Lower, Full Body).
- Cards MUST render with the shared design system styling.
- The list MUST account for safe area insets and remain scrollable when content exceeds the viewport.

#### Scenario: Home Shows Saved Workouts After Login
- **GIVEN** the user has saved workouts (mock data provides at least Upper, Lower, and Full Body),
- **WHEN** the user lands on the workouts home screen after login,
- **THEN** each saved workout appears as a card showing its name.

#### Scenario: Many Workouts Scroll
- **GIVEN** the saved workouts exceed the visible screen height,
- **WHEN** the user scrolls the home screen,
- **THEN** all workout cards remain reachable by scrolling.

### Requirement: Workout Card Exercise Detail
Each workout card SHALL list the exercises contained in that workout.
- Each exercise entry MUST show the exercise name and its prescription (sets × reps, and weight when defined).

#### Scenario: Exercises Visible on Card
- **GIVEN** a saved workout contains exercises,
- **WHEN** its card renders on the workouts home screen,
- **THEN** every exercise in that workout is listed with its name and sets × reps prescription.

#### Scenario: Weight Shown When Defined
- **GIVEN** an exercise in a saved workout defines a weight,
- **WHEN** its card renders,
- **THEN** the exercise entry also displays the defined weight.

### Requirement: Workout Data Model
The app SHALL represent workouts with a typed data model separating a workout from its exercises, and SHALL expose workouts to the UI through the app's Redux store.
- A workout MUST have an identifier, a name, and a collection of exercises.
- An exercise MUST have an identifier, a name, a sets count, a reps prescription, and an optional weight.
- The workouts state MUST initially be populated from a mock data source, structured so a real API can replace the mock source without changing the UI.

#### Scenario: Mock Data Feeds the Home Screen
- **WHEN** the workouts home screen loads,
- **THEN** it renders the workouts provided by the mock data source through the Redux store.

#### Scenario: Model Supports Future Backend Wiring
- **WHEN** a real workouts API replaces the mock data source,
- **THEN** the workout and exercise data model used by the UI does not change.
