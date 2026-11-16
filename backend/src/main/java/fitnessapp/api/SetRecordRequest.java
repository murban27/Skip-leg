package fitnessapp.api;

public record SetRecordRequest(
        Integer reps,
        Double weight
) {
}