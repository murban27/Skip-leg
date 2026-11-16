package fitnessapp.api;

import fitnessapp.domain.SetRecord;
import java.time.LocalDateTime;

public record SetRecordResponse(
        Long id,
        Long sessionId,
        String exerciseId,
        int reps,
        double weight,
        LocalDateTime performedAt
) {
    public static SetRecordResponse from(SetRecord setRecord) {
        return new SetRecordResponse(
                setRecord.getId(),
                setRecord.getSessionId(),
                setRecord.getExerciseId(),
                setRecord.getReps(),
                setRecord.getWeight(),
                setRecord.getPerformedAt()
        );
    }
}