package fitnessapp.application;

import fitnessapp.domain.SetRecord;
import fitnessapp.domain.SetRecordRepository;
import fitnessapp.domain.WorkoutSessionRepository;

import java.time.LocalDateTime;

public class RecordSetUseCase {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final SetRecordRepository setRecordRepository;

    public RecordSetUseCase(WorkoutSessionRepository workoutSessionRepository, SetRecordRepository setRecordRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.setRecordRepository = setRecordRepository;
    }

    public SetRecord record(Long sessionId, String exerciseId, int reps, double weight) {
        if (!workoutSessionRepository.findById(sessionId).isPresent()) {
            throw new SessionNotFoundException("Session not found with id: " + sessionId);
        }

        if (reps <= 0) {
            throw new IllegalArgumentException("Reps must be greater than 0");
        }

        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be greater than or equal to 0");
        }

        SetRecord setRecord = new SetRecord();
        setRecord.setSessionId(sessionId);
        setRecord.setExerciseId(exerciseId);
        setRecord.setReps(reps);
        setRecord.setWeight(weight);
        setRecord.setPerformedAt(LocalDateTime.now());

        return setRecordRepository.save(setRecord);
    }
}