package fitnessapp.application;

import fitnessapp.domain.ActivityRepository;

/**
 * Deletes an existing activity.
 */
public class DeleteActivityUseCase {

    private final ActivityRepository activityRepository;

    public DeleteActivityUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * @throws ActivityNotFoundException if no activity exists with the given id
     */
    public void delete(String id) {
        if (!activityRepository.existsById(id)) {
            throw new ActivityNotFoundException(id);
        }
        activityRepository.deleteById(id);
    }
}
