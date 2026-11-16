package fitnessapp.api;

import fitnessapp.domain.Exercise;
import java.util.List;

public record ExerciseResponse(
        Long id,
        String name,
        String notes,
        List<String> bodyParts
) {
    public static ExerciseResponse from(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getNotes(),
                exercise.getBodyParts()
        );
    }
}