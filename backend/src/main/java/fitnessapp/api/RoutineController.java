package fitnessapp.api;

import fitnessapp.application.*;
import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final CreateRoutineUseCase createRoutineUseCase;
    private final ListRoutinesUseCase listRoutinesUseCase;
    private final GetRoutineUseCase getRoutineUseCase;
    private final DeleteRoutineUseCase deleteRoutineUseCase;
    private final UpdateRoutineUseCase updateRoutineUseCase;

    public RoutineController(CreateRoutineUseCase createRoutineUseCase,
                            ListRoutinesUseCase listRoutinesUseCase,
                            GetRoutineUseCase getRoutineUseCase,
                            DeleteRoutineUseCase deleteRoutineUseCase,
                            UpdateRoutineUseCase updateRoutineUseCase) {
        this.createRoutineUseCase = createRoutineUseCase;
        this.listRoutinesUseCase = listRoutinesUseCase;
        this.getRoutineUseCase = getRoutineUseCase;
        this.deleteRoutineUseCase = deleteRoutineUseCase;
        this.updateRoutineUseCase = updateRoutineUseCase;
    }

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(@RequestBody RoutineRequest request) {
        List<RoutineEntry> entries = request.exercises().stream()
                .map(entry -> {
                    RoutineEntry routineEntry = new RoutineEntry();
                    routineEntry.setExerciseId(String.valueOf(entry.exerciseId()));
                    routineEntry.setDefaultSets(entry.defaultSets());
                    return routineEntry;
                })
                .collect(Collectors.toList());

        Routine routine = createRoutineUseCase.create(request.name(), request.type(), entries);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoutineResponse.from(routine));
    }

    @GetMapping
    public ResponseEntity<List<RoutineResponse>> listRoutines() {
        List<Routine> routines = listRoutinesUseCase.list();
        List<RoutineResponse> responses = routines.stream()
                .map(RoutineResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> getRoutine(@PathVariable Long id) {
        Routine routine = getRoutineUseCase.get(id);
        return ResponseEntity.ok(RoutineResponse.from(routine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponse> updateRoutine(@PathVariable Long id, @RequestBody RoutineRequest request) {
        List<RoutineEntry> entries = request.exercises().stream()
                .map(entry -> {
                    RoutineEntry routineEntry = new RoutineEntry();
                    routineEntry.setExerciseId(String.valueOf(entry.exerciseId()));
                    routineEntry.setDefaultSets(entry.defaultSets());
                    return routineEntry;
                })
                .collect(Collectors.toList());

        Routine routine = updateRoutineUseCase.update(id, request.name(), request.type(), entries);
        return ResponseEntity.ok(RoutineResponse.from(routine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        deleteRoutineUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}