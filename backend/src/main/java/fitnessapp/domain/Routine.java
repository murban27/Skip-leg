package fitnessapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private Long id;
    private String userId;
    private String name;
    private String type;
    private List<RoutineEntry> entries;

    public Routine() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RoutineEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RoutineEntry> entries) {
        this.entries = entries;
    }
}