package fitnessapp.application;

import fitnessapp.domain.Activity;
import fitnessapp.domain.ActivityRepository;

/**
 * Updates an existing activity's type, duration and intensity.
 */
public class UpdateActivityUseCase {

    private final ActivityRepository activityRepository;

    public UpdateActivityUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * @throws ActivityNotFoundException if no activity exists with the given id
     * @throws IllegalArgumentException  if the updated data is invalid
     */
    public Activity update(String id, Activity updated) {
        if (updated == null
                || updated.getType() == null || updated.getType().isBlank()
                || updated.getDuration() <= 0
                || updated.getIntensity() == null || updated.getIntensity().isBlank()) {
            throw new IllegalArgumentException("Activity requires type, positive duration and intensity");
        }
        Activity existing = activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException(id));
        existing.setType(updated.getType());
        existing.setDuration(updated.getDuration());
        existing.setIntensity(updated.getIntensity());
        return activityRepository.save(existing);
    }
}
