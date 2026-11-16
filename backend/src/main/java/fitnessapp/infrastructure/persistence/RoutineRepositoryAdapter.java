package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineEntry;
import fitnessapp.domain.RoutineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoutineRepositoryAdapter implements RoutineRepository {
    private final RoutineJpaRepository routineJpaRepository;

    public RoutineRepositoryAdapter(RoutineJpaRepository routineJpaRepository) {
        this.routineJpaRepository = routineJpaRepository;
    }

    @Override
    public Routine save(Routine routine) {
        RoutineEntity entity = toEntity(routine);
        RoutineEntity savedEntity = routineJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Routine> findById(Long id) {
        return routineJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Routine> findAll() {
        return routineJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        routineJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return routineJpaRepository.existsById(id);
    }

    private RoutineEntity toEntity(Routine routine) {
        RoutineEntity entity = new RoutineEntity();
        entity.setId(routine.getId());
        entity.setUserId(Long.parseLong(routine.getUserId()));
        entity.setName(routine.getName());
        entity.setRoutineType(routine.getType());

        List<RoutineExerciseEntity> entries = routine.getEntries().stream()
                .map(entry -> {
                    RoutineExerciseEntity routineExerciseEntity = new RoutineExerciseEntity();
                    routineExerciseEntity.setExerciseId(Long.parseLong(entry.getExerciseId()));
                    routineExerciseEntity.setDefaultSets(entry.getDefaultSets());
                    routineExerciseEntity.setRoutine(entity);
                    return routineExerciseEntity;
                })
                .collect(Collectors.toList());

        entity.setEntries(entries);
        return entity;
    }

    private Routine toDomain(RoutineEntity entity) {
        Routine routine = new Routine();
        routine.setId(entity.getId());
        routine.setUserId(String.valueOf(entity.getUserId()));
        routine.setName(entity.getName());
        routine.setType(entity.getRoutineType());

        List<RoutineEntry> entries = entity.getEntries().stream()
                .map(entry -> {
                    RoutineEntry routineEntry = new RoutineEntry();
                    routineEntry.setExerciseId(String.valueOf(entry.getExerciseId()));
                    routineEntry.setDefaultSets(entry.getDefaultSets());
                    return routineEntry;
                })
                .collect(Collectors.toList());

        routine.setEntries(entries);
        return routine;
    }
}