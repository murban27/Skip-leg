package fitnessapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkoutSession {
    private Long id;
    private String userId;
    private String routineId;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private List<SessionEntry> entries;

    public WorkoutSession() {
        this.entries = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public List<SessionEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SessionEntry> entries) {
        this.entries = entries;
    }
}