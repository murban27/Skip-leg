# FitnessApp

FitnessApp is a multi-platform application designed for both trainers and clients to track workouts, fitness activities, and progress. Trainers can manage clients, assign training plans, and track progress metrics. Future capabilities include calorie tracking, visual progress photos, and social activity sharing.

## Architecture

### Backend
- **Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Real-Time Updates**: WebSocket

### Mobile
- **Language**: TypeScript
- **Framework**: React Native
- **State Management**: Redux Toolkit
- **API Client**: Axios
- **Navigation**: React Navigation
- **Styling**: React Native StyleSheet API
- **Testing**: Jest + React Testing Library

### Shared
- **Language**: TypeScript
- **Purpose**: Shared logic for activities, authentication, and real-time updates.

## Setup

### Prerequisites
- Node.js (v18+)
- Yarn (v1.22+)
- Java (v11+)
- Maven (v3.8+)
- Android Studio (for Android development)
- Xcode (for iOS development)

### Backend Setup
1. Navigate to the `backend` directory:
   ```bash
   cd backend
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the backend:
   ```bash
   mvn spring-boot:run
   ```

### Mobile Setup
1. Navigate to the `mobile` directory:
   ```bash
   cd mobile
   ```
2. Install dependencies:
   ```bash
   yarn install
   ```
3. Run the React Native app:
   ```bash
   npx react-native run-android
   # or
   npx react-native run-ios
   ```

### Shared Setup
1. Navigate to the `shared` directory:
   ```bash
   cd shared
   ```
2. Install dependencies:
   ```bash
   yarn install
   ```
3. Build the shared module:
   ```bash
   yarn build
   ```

## OpenSpec

### CodeGraph

- **CodeGraph**: Use the `codegraph` skill for symbol navigation, dependency resolution, and reference tracing across the codebase. Do not rely solely on plain-text search (`grep`/`find`) when inspecting architecture or refactoring types.

This project uses OpenSpec for spec-driven development. To propose, implement, or archive a change, use the following commands:

- **Propose a Change**: `/opsx-propose <change-name>`
- **Implement a Change**: `/opsx-apply <change-name>`
- **Archive a Change**: `/opsx-archive <change-name>`

## Documentation

- [Architecture](ARCHITECTURE.md)
- [API Documentation](backend/src/main/resources/static/docs/api-docs.yaml)
- [OpenSpec Artifacts](openspec/)