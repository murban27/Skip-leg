package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.SessionEntry;
import fitnessapp.domain.WorkoutSession;
import fitnessapp.domain.WorkoutSessionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WorkoutSessionRepositoryAdapter implements WorkoutSessionRepository {
    private final WorkoutSessionJpaRepository workoutSessionJpaRepository;

    public WorkoutSessionRepositoryAdapter(WorkoutSessionJpaRepository workoutSessionJpaRepository) {
        this.workoutSessionJpaRepository = workoutSessionJpaRepository;
    }

    @Override
    public WorkoutSession save(WorkoutSession session) {
        WorkoutSessionEntity entity = toEntity(session);
        if (entity.getStartedAt() == null) {
            entity.setStartedAt(LocalDateTime.now());
        }
        if (entity.getStatus() == null) {
            entity.setStatus("IN_PROGRESS");
        }
        WorkoutSessionEntity savedEntity = workoutSessionJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<WorkoutSession> findById(Long id) {
        return workoutSessionJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<WorkoutSession> findAll() {
        return workoutSessionJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private WorkoutSessionEntity toEntity(WorkoutSession session) {
        WorkoutSessionEntity entity = new WorkoutSessionEntity();
        entity.setId(session.getId());
        entity.setUserId(Long.parseLong(session.getUserId()));
        entity.setRoutineId(Long.parseLong(session.getRoutineId()));
        entity.setStatus(session.getStatus());
        entity.setStartedAt(session.getStartedAt());
        entity.setCompletedAt(session.getCompletedAt());

        List<SessionExerciseEntity> entries = session.getEntries().stream()
                .map(entry -> {
                    SessionExerciseEntity sessionExerciseEntity = new SessionExerciseEntity();
                    sessionExerciseEntity.setExerciseId(Long.parseLong(entry.getExerciseId()));
                    sessionExerciseEntity.setExerciseName(entry.getExerciseName());
                    sessionExerciseEntity.setDefaultSets(entry.getDefaultSets());
                    sessionExerciseEntity.setSession(entity);
                    return sessionExerciseEntity;
                })
                .collect(Collectors.toList());

        entity.setEntries(entries);
        return entity;
    }

    private WorkoutSession toDomain(WorkoutSessionEntity entity) {
        WorkoutSession session = new WorkoutSession();
        session.setId(entity.getId());
        session.setUserId(String.valueOf(entity.getUserId()));
        session.setRoutineId(String.valueOf(entity.getRoutineId()));
        session.setStatus(entity.getStatus());
        session.setStartedAt(entity.getStartedAt());
        session.setCompletedAt(entity.getCompletedAt());

        List<SessionEntry> entries = entity.getEntries().stream()
                .map(entry -> {
                    SessionEntry sessionEntry = new SessionEntry();
                    sessionEntry.setExerciseId(String.valueOf(entry.getExerciseId()));
                    sessionEntry.setExerciseName(entry.getExerciseName());
                    sessionEntry.setDefaultSets(entry.getDefaultSets());
                    return sessionEntry;
                })
                .collect(Collectors.toList());

        session.setEntries(entries);
        return session;
    }
}