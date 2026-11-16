package fitnessapp.api;

import fitnessapp.domain.Routine;
import java.util.List;
import java.util.stream.Collectors;

public record RoutineResponse(
        Long id,
        String name,
        String type,
        List<RoutineEntryResponse> exercises
) {
    public static RoutineResponse from(Routine routine) {
        List<RoutineEntryResponse> entries = routine.getEntries().stream()
                .map(entry -> new RoutineEntryResponse(
                        entry.getExerciseId(),
                        entry.getExerciseName(),
                        entry.getDefaultSets()
                ))
                .collect(Collectors.toList());

        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getType(),
                entries
        );
    }
}