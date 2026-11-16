package fitnessapp.application;

import fitnessapp.domain.Exercise;
import fitnessapp.domain.ExerciseRepository;

import java.util.List;

public class ListExercisesUseCase {
    private final ExerciseRepository exerciseRepository;

    public ListExercisesUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> list() {
        return exerciseRepository.findAll();
    }
}