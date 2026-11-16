package fitnessapp.application;

import fitnessapp.domain.WorkoutSession;
import fitnessapp.domain.WorkoutSessionRepository;

import java.time.LocalDateTime;

public class CompleteSessionUseCase {
    private final WorkoutSessionRepository workoutSessionRepository;

    public CompleteSessionUseCase(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public WorkoutSession complete(Long id) {
        WorkoutSession session = workoutSessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + id));

        if ("COMPLETED".equals(session.getStatus())) {
            throw new SessionAlreadyCompletedException("Session with id " + id + " is already completed");
        }

        session.setStatus("COMPLETED");
        session.setCompletedAt(LocalDateTime.now());

        return workoutSessionRepository.save(session);
    }
}