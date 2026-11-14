package fitnessapp.application;

import fitnessapp.domain.Activity;
import fitnessapp.domain.ActivityRepository;
import java.util.List;

/**
 * Retrieves all logged activities.
 */
public class ListActivitiesUseCase {

    private final ActivityRepository activityRepository;

    public ListActivitiesUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> list() {
        return activityRepository.findAll();
    }
}
