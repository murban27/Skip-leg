package fitnessapp.application;

import fitnessapp.domain.Activity;
import fitnessapp.domain.ActivityRepository;
import java.util.UUID;

/**
 * Logs a new activity, assigning its identifier.
 */
public class LogActivityUseCase {

    private final ActivityRepository activityRepository;

    public LogActivityUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * @throws IllegalArgumentException if the activity data is incomplete or invalid
     */
    public Activity log(Activity activity) {
        validate(activity);
        activity.setId(UUID.randomUUID().toString());
        return activityRepository.save(activity);
    }

    private void validate(Activity activity) {
        if (activity == null
                || activity.getUserId() == null || activity.getUserId().isBlank()
                || activity.getType() == null || activity.getType().isBlank()
                || activity.getDuration() <= 0
                || activity.getIntensity() == null || activity.getIntensity().isBlank()) {
            throw new IllegalArgumentException("Activity requires userId, type, positive duration and intensity");
        }
    }
}
