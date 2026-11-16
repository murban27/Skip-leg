package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.Exercise;
import fitnessapp.domain.ExerciseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExerciseRepositoryAdapter implements ExerciseRepository {
    private final ExerciseJpaRepository exerciseJpaRepository;

    public ExerciseRepositoryAdapter(ExerciseJpaRepository exerciseJpaRepository) {
        this.exerciseJpaRepository = exerciseJpaRepository;
    }

    @Override
    public Exercise save(Exercise exercise) {
        ExerciseEntity entity = toEntity(exercise);
        ExerciseEntity savedEntity = exerciseJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return exerciseJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private ExerciseEntity toEntity(Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setId(exercise.getId());
        entity.setUserId(Long.parseLong(exercise.getUserId()));
        entity.setName(exercise.getName());
        entity.setNotes(exercise.getNotes());
        entity.setBodyParts(exercise.getBodyParts());
        return entity;
    }

    private Exercise toDomain(ExerciseEntity entity) {
        Exercise exercise = new Exercise();
        exercise.setId(entity.getId());
        exercise.setUserId(String.valueOf(entity.getUserId()));
        exercise.setName(entity.getName());
        exercise.setNotes(entity.getNotes());
        exercise.setBodyParts(entity.getBodyParts());
        return exercise;
    }
}