package fitnessapp.api;

import fitnessapp.domain.WorkoutSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SessionResponse(
        Long id,
        Long routineId,
        String status,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        List<SessionEntryResponse> entries
) {
    public static SessionResponse from(WorkoutSession session) {
        List<SessionEntryResponse> entries = session.getEntries().stream()
                .map(entry -> new SessionEntryResponse(
                        entry.getExerciseId(),
                        entry.getExerciseName(),
                        entry.getDefaultSets()
                ))
                .collect(Collectors.toList());

        return new SessionResponse(
                session.getId(),
                session.getRoutineId() == null ? null : Long.parseLong(session.getRoutineId()),
                session.getStatus(),
                session.getStartedAt(),
                session.getCompletedAt(),
                entries
        );
    }
}