package fitnessapp.application;

import fitnessapp.domain.WorkoutSession;
import fitnessapp.domain.WorkoutSessionRepository;

import java.util.Optional;

public class GetSessionUseCase {
    private final WorkoutSessionRepository workoutSessionRepository;

    public GetSessionUseCase(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public WorkoutSession get(Long id) {
        Optional<WorkoutSession> session = workoutSessionRepository.findById(id);
        if (session.isEmpty()) {
            throw new SessionNotFoundException("Session not found with id: " + id);
        }
        return session.get();
    }
}