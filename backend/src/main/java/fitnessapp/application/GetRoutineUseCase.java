package fitnessapp.application;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineRepository;

import java.util.Optional;

public class GetRoutineUseCase {
    private final RoutineRepository routineRepository;

    public GetRoutineUseCase(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Routine get(Long id) {
        Optional<Routine> routine = routineRepository.findById(id);
        if (routine.isEmpty()) {
            throw new RoutineNotFoundException("Routine not found with id: " + id);
        }
        return routine.get();
    }
}