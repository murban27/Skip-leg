package fitnessapp.domain;

import java.util.Objects;

/**
 * Domain entity for a fitness activity. Pure Java — no framework dependencies.
 */
public class Activity {

    private String id;
    private String userId;
    private String type;
    private int duration;
    private String intensity;

    public Activity() {
    }

    public Activity(String id, String userId, String type, int duration, String intensity) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.duration = duration;
        this.intensity = intensity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
