package fitnessapp.api;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record ExerciseRequest(
        @NotBlank String name,
        String notes,
        List<String> bodyParts
) {
}