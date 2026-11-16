package fitnessapp.application;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineRepository;

import java.util.List;

public class ListRoutinesUseCase {
    private final RoutineRepository routineRepository;

    public ListRoutinesUseCase(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public List<Routine> list() {
        return routineRepository.findAll();
    }
}