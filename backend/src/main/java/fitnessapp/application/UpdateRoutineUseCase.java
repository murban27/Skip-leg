package fitnessapp.application;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineEntry;
import fitnessapp.domain.RoutineRepository;

import java.util.List;

public class UpdateRoutineUseCase {
    private final RoutineRepository routineRepository;

    public UpdateRoutineUseCase(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Routine update(Long id, String name, String type, List<RoutineEntry> entries) {
        Routine existingRoutine = routineRepository.findById(id)
                .orElseThrow(() -> new RoutineNotFoundException("Routine not found with id: " + id));

        existingRoutine.setName(name);
        existingRoutine.setType(type);
        existingRoutine.setEntries(entries);

        return routineRepository.save(existingRoutine);
    }
}