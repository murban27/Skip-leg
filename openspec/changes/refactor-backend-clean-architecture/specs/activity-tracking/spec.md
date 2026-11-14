# Spec Delta

## MODIFIED Requirements

### Requirement: Log Activity
The system SHALL allow users to log workout activities with details such as type, duration, and intensity, and SHALL persist them so they survive application restarts.

#### Scenario: Successful Activity Logging
- **WHEN** a user submits valid activity details
- **THEN** the system records the activity with a generated identifier and returns the stored activity.

#### Scenario: Logging Activity with Invalid Data
- **WHEN** a user submits incomplete or invalid activity details
- **THEN** the system returns an error indicating invalid data.

#### Scenario: Activity Survives Restart
- **WHEN** an activity has been logged and the application is restarted
- **THEN** the logged activity is still retrievable.

### Requirement: Retrieve Activity History
The system SHALL allow users to retrieve their logged activities from persistent storage.

#### Scenario: Successful Activity Retrieval
- **WHEN** a user requests their activity history
- **THEN** the system returns a list of logged activities.

#### Scenario: Retrieval with No Activities
- **WHEN** a user requests their activity history with no logged activities
- **THEN** the system returns an empty list.

### Requirement: Update Activity
The system SHALL allow users to update their logged activities in persistent storage.

#### Scenario: Successful Activity Update
- **WHEN** a user submits valid updates to an existing activity
- **THEN** the system updates the activity and returns a success response.

#### Scenario: Update with Invalid Data
- **WHEN** a user submits invalid updates to an activity
- **THEN** the system returns an error indicating invalid data.

#### Scenario: Update of Non-Existent Activity
- **WHEN** a user submits updates for an activity identifier that does not exist
- **THEN** the system returns an error indicating activity not found.

### Requirement: Delete Activity
The system SHALL allow users to delete their logged activities from persistent storage.

#### Scenario: Successful Activity Deletion
- **WHEN** a user requests to delete an activity
- **THEN** the system deletes the activity and returns a success response.

#### Scenario: Deletion of Non-Existent Activity
- **WHEN** a user requests to delete a non-existent activity
- **THEN** the system returns an error indicating activity not found.
