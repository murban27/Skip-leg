package fitnessapp.application;

import fitnessapp.domain.Exercise;
import fitnessapp.domain.ExerciseRepository;
import fitnessapp.domain.BodyPart;

import java.util.List;

public class CreateExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    public CreateExerciseUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise create(String name, String notes, List<String> bodyParts) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise name cannot be blank");
        }

        if (bodyParts == null || bodyParts.isEmpty()) {
            throw new IllegalArgumentException("Exercise must have at least one body part");
        }

        for (String bodyPart : bodyParts) {
            try {
                BodyPart.valueOf(bodyPart.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid body part: " + bodyPart);
            }
        }

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setNotes(notes);
        exercise.setBodyParts(bodyParts);

        return exerciseRepository.save(exercise);
    }
}