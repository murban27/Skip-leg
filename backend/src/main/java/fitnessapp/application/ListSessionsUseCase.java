package fitnessapp.application;

import fitnessapp.domain.WorkoutSession;
import fitnessapp.domain.WorkoutSessionRepository;

import java.util.List;

public class ListSessionsUseCase {
    private final WorkoutSessionRepository workoutSessionRepository;

    public ListSessionsUseCase(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public List<WorkoutSession> list() {
        return workoutSessionRepository.findAll();
    }
}