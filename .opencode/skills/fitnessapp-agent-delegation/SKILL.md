# FitnessApp Agent Delegation Skill

## Purpose
This skill defines the **automatic delegation rules** for the FitnessApp project. It ensures that tasks are automatically delegated to the appropriate sub-agents (`@mobile-dev`, `@backend-dev`, `@test-engineer`, `@researcher`) based on **keywords**, **file paths**, and **technology stacks**. Each sub-agent uses its **explicitly configured model** (e.g., `mistral/codestral-latest`).

---

## Delegation Rules

The `@orchestrator` agent **automatically delegates** tasks to sub-agents based on the following rules:

| **Task Type**               | **Sub-Agent**       | **Model**                     | **Trigger Keywords/Paths**          |
|-----------------------------|---------------------|--------------------------------|--------------------------------------|
| Mobile UI Development       | `@mobile-dev`       | `mistral/codestral-latest`    | `Expo`, `React Native`, `mobile/`   |
| Backend Development         | `@backend-dev`      | `mistral/codestral-latest`    | `Java`, `Spring Boot`, `backend/`   |
| Testing                     | `@test-engineer`    | `mistral/codestral-latest`    | `Jest`, `React Testing Library`, `MSW`, `test` |
| Research and Analysis       | `@researcher`       | `mistral/codestral-latest`    | `Research`, `Expo compatibility`, `dependency` |
| OpenSpec Management         | `@orchestrator`     | `mistral/codestral-latest`    | `/opsx-`, `OpenSpec`, `proposal.md`, `tasks.md` |

---

## Workflow

### 1. Task Parsing
The `@orchestrator` reads the **OpenSpec `tasks.md`** file and parses each task. For each task, it extracts:
- **Keywords** (e.g., "Expo", "Java", "Jest")
- **File paths** (e.g., `mobile/`, `backend/`)
- **Technology stacks** (e.g., React Native, Spring Boot)

### 2. Agent Selection
The `@orchestrator` matches the **keywords/paths** to the **delegation rules** (see table above). If a match is found, the task is **automatically delegated** to the corresponding sub-agent.

### 3. Model Assignment
Each sub-agent uses its **explicitly configured model** (e.g., `mistral/codestral-latest`). This is defined in the sub-agent's configuration file (e.g., `mobile-dev.md`).

### 4. Task Execution
The sub-agent executes the task **in its own context** (e.g., `D:\FitnesApp\mobile` for `@mobile-dev`). The sub-agent **reports progress** back to the `@orchestrator`.

### 5. Verification
The `@orchestrator` verifies that the task is completed and **updates the OpenSpec `tasks.md`** file.

---

## Example Delegation Scenarios

### Scenario 1: Mobile UI Task
- **Task**: *"Implement the `LoginScreen` component in Expo."*
- **Keywords**: `Expo`, `LoginScreen`, `mobile/`
- **Delegation**:
  - **Sub-Agent**: `@mobile-dev`
  - **Model**: `mistral/codestral-latest` (as configured in `mobile-dev.md`)
  - **Instructions**: *"Implement the `LoginScreen` component using Expo Bare Workflow and TypeScript. Ensure the component is compatible with Redux Toolkit and React Navigation."*

### Scenario 2: Backend Task
- **Task**: *"Implement a new RESTful API endpoint for activities."*
- **Keywords**: `RESTful API`, `activities`, `backend/`
- **Delegation**:
  - **Sub-Agent**: `@backend-dev`
  - **Model**: `mistral/codestral-latest` (as configured in `backend-dev.md`)
  - **Instructions**: *"Implement a new RESTful API endpoint for activities using Java and Spring Boot. Ensure the endpoint is compatible with the mobile UI."*

### Scenario 3: Testing Task
- **Task**: *"Write unit tests for the `ActivityService` using Jest."*
- **Keywords**: `Jest`, `ActivityService`, `test`
- **Delegation**:
  - **Sub-Agent**: `@test-engineer`
  - **Model**: `mistral/codestral-latest` (as configured in `test-engineer.md`)
  - **Instructions**: *"Write unit tests for the `ActivityService` using Jest and React Testing Library. Ensure all tests pass."*

### Scenario 4: Research Task
- **Task**: *"Investigate WebSocket compatibility with Expo."*
- **Keywords**: `WebSocket`, `Expo`, `compatibility`
- **Delegation**:
  - **Sub-Agent**: `@researcher`
  - **Model**: `mistral/codestral-latest` (as configured in `researcher.md`)
  - **Instructions**: *"Investigate WebSocket compatibility with Expo Bare Workflow. Provide recommendations for resolving any issues."*

### Scenario 5: OpenSpec Task
- **Task**: *"Update the OpenSpec `proposal.md` for the Expo migration."*
- **Keywords**: `/opsx-`, `OpenSpec`, `proposal.md`
- **Delegation**:
  - **Sub-Agent**: `@orchestrator` (self)
  - **Model**: `mistral/codestral-latest` (as configured in `orchestrator.md`)
  - **Instructions**: *"Update the OpenSpec `proposal.md` to reflect the Expo migration. Ensure all artifacts are coherent."*

---

## Integration with OpenCode

To **automate this delegation** in OpenCode:

1. **Use the `subagent` tool** to delegate tasks to sub-agents:
   ```bash
   subagent --agent mobile-dev --description "Implement LoginScreen" --prompt "Implement the LoginScreen component using Expo Bare Workflow and TypeScript." --workdir D:\FitnesApp\mobile
   ```

2. **Configure the `AGENTS.md` file** to include the delegation rules (see `AGENTS.md` in the project root).

3. **Use the `openspec-apply-change` skill** to automate the delegation of tasks based on the OpenSpec `tasks.md` file.

---

## Notes
- This skill is designed to work with **OpenCode** and **OpenSpec** for the FitnessApp project.
- The delegation rules are **strictly followed** to ensure tasks are assigned to the correct sub-agents.
- Each sub-agent uses its **explicitly configured model** (e.g., `mistral/codestral-latest`).
- The `@orchestrator` retains full control over the project and verifies task completion.