package fitnessapp.api;

public record SessionEntryResponse(
        String exerciseId,
        String exerciseName,
        int defaultSets
) {
}