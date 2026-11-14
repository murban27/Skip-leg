# Docker Compose Setup

## Services
- **PostgreSQL**: Database service for storing user data and activities.
- **Backend**: Spring Boot application for handling API requests.

## Configuration
- **PostgreSQL**:
  - Image: `postgres:15`
  - Environment Variables:
    - `POSTGRES_USER`: `fitnessapp`
    - `POSTGRES_PASSWORD`: `fitnessapp`
    - `POSTGRES_DB`: `fitnessapp`
  - Port: `5432`
- **Backend**:
  - Build Context: `./backend`
  - Dockerfile: `Dockerfile`
  - Port: `8080`

## Usage
1. **Start Services**:
   ```bash
   docker-compose up -d
   ```
2. **Stop Services**:
   ```bash
   docker-compose down
   ```
3. **Access Services**:
   - PostgreSQL: `localhost:5432`
   - Backend: `http://localhost:8080`