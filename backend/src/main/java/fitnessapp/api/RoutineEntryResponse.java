package fitnessapp.api;

public record RoutineEntryResponse(
        String exerciseId,
        String exerciseName,
        int defaultSets
) {
}