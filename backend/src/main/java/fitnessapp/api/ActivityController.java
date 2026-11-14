package fitnessapp.api;

import fitnessapp.application.ListActivitiesUseCase;
import fitnessapp.application.LogActivityUseCase;
import fitnessapp.application.UpdateActivityUseCase;
import fitnessapp.application.DeleteActivityUseCase;
import fitnessapp.domain.Activity;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Activity endpoints consumed by the React Native mobile app.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final LogActivityUseCase logActivityUseCase;
    private final ListActivitiesUseCase listActivitiesUseCase;
    private final UpdateActivityUseCase updateActivityUseCase;
    private final DeleteActivityUseCase deleteActivityUseCase;

    public ActivityController(
            LogActivityUseCase logActivityUseCase,
            ListActivitiesUseCase listActivitiesUseCase,
            UpdateActivityUseCase updateActivityUseCase,
            DeleteActivityUseCase deleteActivityUseCase) {
        this.logActivityUseCase = logActivityUseCase;
        this.listActivitiesUseCase = listActivitiesUseCase;
        this.updateActivityUseCase = updateActivityUseCase;
        this.deleteActivityUseCase = deleteActivityUseCase;
    }

    @PostMapping
    public ActivityResponse logActivity(@Valid @RequestBody ActivityRequest request) {
        Activity logged = logActivityUseCase.log(toDomain(request));
        return new ActivityResponse(logged);
    }

    @GetMapping
    public List<ActivityResponse> getActivities() {
        return listActivitiesUseCase.list().stream()
                .map(ActivityResponse::new)
                .toList();
    }

    @PutMapping("/{id}")
    public ActivityResponse updateActivity(@PathVariable String id, @Valid @RequestBody ActivityRequest request) {
        Activity updated = updateActivityUseCase.update(id, toDomain(request));
        return new ActivityResponse(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable String id) {
        deleteActivityUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Activity toDomain(ActivityRequest request) {
        Activity activity = new Activity();
        activity.setUserId(request.getUserId());
        activity.setType(request.getType());
        activity.setDuration(request.getDuration());
        activity.setIntensity(request.getIntensity());
        return activity;
    }
}
