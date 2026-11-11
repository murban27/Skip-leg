package fitnessapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    // Mock database for activities
    private static final List<Activity> activities = new ArrayList<>();

    @PostMapping
    public Activity logActivity(@RequestBody Activity activity) {
        activity.setId(UUID.randomUUID().toString());
        activities.add(activity);
        return activity;
    }

    @GetMapping
    public List<Activity> getActivities() {
        return activities;
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable String id, @RequestBody Activity updatedActivity) {
        for (Activity activity : activities) {
            if (activity.getId().equals(id)) {
                activity.setType(updatedActivity.getType());
                activity.setDuration(updatedActivity.getDuration());
                activity.setIntensity(updatedActivity.getIntensity());
                return activity;
            }
        }
        throw new RuntimeException("Activity not found");
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable String id) {
        activities.removeIf(activity -> activity.getId().equals(id));
    }

    // Inner class for Activity
    public static class Activity {
        private String id;
        private String userId;
        private String type;
        private int duration;
        private String intensity;

        // Getters and Setters
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
}