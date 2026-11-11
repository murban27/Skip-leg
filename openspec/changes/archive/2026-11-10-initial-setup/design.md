# Design

## Context

The **FitnessApp** project is a monorepo with the following structure:
- **Backend**: Java/Spring Boot for RESTful APIs and WebSocket communication.
- **Mobile**: Native development (Kotlin for Android, Swift for iOS).
- **Database**: PostgreSQL for structured data storage.
- **Tooling**: Gradle for build management, CodeGraph for symbol navigation, and OpenSpec for spec-driven development.

The system must support:
- Mock authentication for local development.
- Activity tracking and progress monitoring.
- Real-time updates via WebSockets.
- Structured database schema for users, activities, and progress.


## Goals / Non-Goals

**Goals:**
- Establish a scalable and maintainable architecture for the FitnessApp.
- Define clear boundaries between backend, mobile, and database layers.
- Enable quick setup and validation using Docker Compose.
- Simulate authentication for local development.

**Non-Goals:**
- Implement production-ready authentication (future enhancement).
- Develop a web frontend (mobile-only for now).
- Optimize for horizontal scaling (initial focus on vertical scalability).
- Finalize UI/UX design (placeholder for future discussion).


## Decisions

### 1. Backend Technology: Spring Boot
- **Decision**: Use Spring Boot for the backend.
- **Rationale**: Robust, scalable, and enterprise-ready. Provides built-in support for REST, WebSockets, and mock authentication.
- **Alternatives Considered**:
  - **Node.js**: Lightweight and fast, but lacks the enterprise-grade features of Spring Boot.
  - **Micronaut**: Modern and lightweight, but smaller ecosystem compared to Spring Boot.


### 2. Mobile Technology: Native (Kotlin/Swift)
- **Decision**: Use native development for mobile apps.
- **Rationale**: Better performance and access to platform-specific features.
- **Alternatives Considered**:
  - **React Native**: Cross-platform, but limited access to native APIs.
  - **Flutter**: High performance, but smaller ecosystem and Dart learning curve.


### 3. API Communication: REST
- **Decision**: Use REST for API communication.
- **Rationale**: Simple, widely adopted, and caching-friendly.
- **Alternatives Considered**:
  - **GraphQL**: Flexible queries, but adds complexity to the backend.


### 4. Authentication: Mock JWT
- **Decision**: Use mock JWT for local development.
- **Rationale**: Simplifies development and testing without compromising security in production.
- **Alternatives Considered**:
  - **OAuth2**: Industry standard, but adds complexity for local development.
  - **Session-Based Auth**: Less scalable and harder to manage across platforms.


### 5. Real-Time Features: WebSockets
- **Decision**: Use WebSockets for real-time updates.
- **Rationale**: Efficient and low-latency communication.
- **Alternatives Considered**:
  - **Polling**: Simpler, but less efficient and higher latency.


### 6. Database: PostgreSQL (Single Schema)
- **Decision**: Use PostgreSQL with a single schema.
- **Rationale**: Structured data modeling, strong consistency, and ACID transactions.
- **Alternatives Considered**:
  - **MongoDB**: Flexible schema, but weaker querying capabilities.
  - **Firebase**: Real-time updates, but less control over data structure.


### 7. Quick Setup: Docker Compose
- **Decision**: Use Docker Compose for local development and validation.
- **Rationale**: Simplifies setup and ensures consistency across environments.
- **Alternatives Considered**:
  - **Manual Setup**: Time-consuming and error-prone.
  - **Vagrant**: More complex and resource-intensive.


### 8. UI/UX Design
- **Decision**: Defer detailed UI/UX design to a future phase.
- **Rationale**: Focus on core functionality and architecture first.
- **Alternatives Considered**:
  - **Early Design**: Risk of rework due to changing requirements.


## Risks / Trade-offs

### 1. WebSocket Connection Management
- **Risk**: Managing WebSocket connections at scale.
- **Mitigation**: Use a dedicated WebSocket service (e.g., Spring WebSocket) and implement connection pooling.


### 2. Mock Authentication
- **Risk**: Mock tokens may not reflect production behavior.
- **Mitigation**: Clearly document mock authentication for local development only.


### 3. Database Schema Changes
- **Risk**: Schema changes breaking existing functionality.
- **Mitigation**: Use database migrations (e.g., Flyway) and test changes in a staging environment.


### 4. Cross-Platform Mobile Development
- **Risk**: Maintaining separate codebases for Android and iOS.
- **Mitigation**: Use shared libraries for business logic and platform-specific UI components.


### 5. Docker Compose Complexity
- **Risk**: Managing multiple services in Docker Compose.
- **Mitigation**: Use lightweight images and optimize resource usage.


## Migration Plan

1. **Initial Setup**:
   - Set up the monorepo structure with backend, mobile, and shared modules.
   - Configure Gradle for build management.
   - Initialize PostgreSQL database with the defined schema.
   - Create a `docker-compose.yml` file for local development and validation.

2. **Backend Development**:
   - Implement RESTful endpoints for activity tracking and progress updates.
   - Integrate WebSocket for real-time communication.
   - Implement mock authentication for local development.

3. **Mobile Development**:
   - Develop native apps for Android (Kotlin) and iOS (Swift).
   - Implement activity logging and real-time updates.

4. **Testing**:
   - Write unit and integration tests for backend and mobile components.
   - Test real-time updates and database interactions.

5. **Deployment**:
   - Deploy the backend to a cloud provider (e.g., AWS, GCP).
   - Publish mobile apps to the App Store and Google Play.

6. **Rollback Strategy**:
   - Use database backups and versioned deployments to roll back if issues arise.