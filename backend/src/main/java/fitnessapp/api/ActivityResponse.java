package fitnessapp.api;

import fitnessapp.domain.Activity;

/** Response body describing a stored activity. */
public class ActivityResponse {

    private String id;
    private String userId;
    private String type;
    private int duration;
    private String intensity;

    public ActivityResponse() {
    }

    public ActivityResponse(Activity activity) {
        this.id = activity.getId();
        this.userId = activity.getUserId();
        this.type = activity.getType();
        this.duration = activity.getDuration();
        this.intensity = activity.getIntensity();
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
}
