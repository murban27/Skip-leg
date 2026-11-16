package fitnessapp.application;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineEntry;
import fitnessapp.domain.RoutineRepository;
import fitnessapp.domain.RoutineType;

import java.util.List;

public class CreateRoutineUseCase {
    private final RoutineRepository routineRepository;

    public CreateRoutineUseCase(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Routine create(String name, String type, List<RoutineEntry> entries) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Routine name cannot be blank");
        }

        try {
            RoutineType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid routine type: " + type);
        }

        if (entries == null || entries.isEmpty()) {
            throw new IllegalArgumentException("Routine must have at least one entry");
        }

        Routine routine = new Routine();
        routine.setName(name);
        routine.setType(type);
        routine.setEntries(entries);

        return routineRepository.save(routine);
    }
}