package fitnessapp.api;

public record RoutineEntryRequest(
        Long exerciseId,
        Integer defaultSets
) {
}