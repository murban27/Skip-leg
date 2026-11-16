package fitnessapp.application;

import fitnessapp.domain.SetRecord;
import fitnessapp.domain.SetRecordRepository;
import fitnessapp.domain.WorkoutSessionRepository;

import java.util.List;

public class ListSessionSetsUseCase {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final SetRecordRepository setRecordRepository;

    public ListSessionSetsUseCase(WorkoutSessionRepository workoutSessionRepository, SetRecordRepository setRecordRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.setRecordRepository = setRecordRepository;
    }

    public List<SetRecord> list(Long sessionId) {
        if (!workoutSessionRepository.findById(sessionId).isPresent()) {
            throw new SessionNotFoundException("Session not found with id: " + sessionId);
        }

        return setRecordRepository.findBySessionId(sessionId);
    }
}