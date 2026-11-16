package fitnessapp.api;

import fitnessapp.application.CreateExerciseUseCase;
import fitnessapp.application.ListExercisesUseCase;
import fitnessapp.domain.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final CreateExerciseUseCase createExerciseUseCase;
    private final ListExercisesUseCase listExercisesUseCase;

    public ExerciseController(CreateExerciseUseCase createExerciseUseCase, ListExercisesUseCase listExercisesUseCase) {
        this.createExerciseUseCase = createExerciseUseCase;
        this.listExercisesUseCase = listExercisesUseCase;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@RequestBody ExerciseRequest request) {
        Exercise exercise = createExerciseUseCase.create(request.name(), request.notes(), request.bodyParts());
        return ResponseEntity.status(HttpStatus.CREATED).body(ExerciseResponse.from(exercise));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> listExercises() {
        List<Exercise> exercises = listExercisesUseCase.list();
        List<ExerciseResponse> responses = exercises.stream()
                .map(ExerciseResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}