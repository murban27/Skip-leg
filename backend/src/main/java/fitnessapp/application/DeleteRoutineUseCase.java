package fitnessapp.application;

import fitnessapp.domain.RoutineRepository;

public class DeleteRoutineUseCase {
    private final RoutineRepository routineRepository;

    public DeleteRoutineUseCase(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public void delete(Long id) {
        if (!routineRepository.existsById(id)) {
            throw new RoutineNotFoundException("Routine not found with id: " + id);
        }
        routineRepository.deleteById(id);
    }
}