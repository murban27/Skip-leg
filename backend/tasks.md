# Backend Tasks (superseded)

The original WebSocket/database checklist is complete and this file is superseded by the
OpenSpec change `openspec/changes/refactor-backend-clean-architecture/`, which restructured
the backend into Clean Architecture layers:

- `fitnessapp.domain` — entities (`User`, `Activity`) and repository ports
- `fitnessapp.application` — use cases (register/login, activity CRUD)
- `fitnessapp.infrastructure` — JPA persistence adapters, JWT filter, WebSocket config
- `fitnessapp.api` — REST controllers + DTOs serving the React Native mobile app
- `fitnessapp.config` — composition root (bean wiring)

Run tests with `mvn test` from `backend/`.
