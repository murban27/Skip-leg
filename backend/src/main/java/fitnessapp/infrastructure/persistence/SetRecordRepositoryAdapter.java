package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.SetRecord;
import fitnessapp.domain.SetRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SetRecordRepositoryAdapter implements SetRecordRepository {
    private final SetRecordJpaRepository setRecordJpaRepository;

    public SetRecordRepositoryAdapter(SetRecordJpaRepository setRecordJpaRepository) {
        this.setRecordJpaRepository = setRecordJpaRepository;
    }

    @Override
    public SetRecord save(SetRecord setRecord) {
        SetRecordEntity entity = toEntity(setRecord);
        SetRecordEntity savedEntity = setRecordJpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public List<SetRecord> findBySessionId(Long sessionId) {
        return setRecordJpaRepository.findBySessionId(sessionId).stream()
                .map(this::toDomain)
                .toList();
    }

    private SetRecordEntity toEntity(SetRecord setRecord) {
        SetRecordEntity entity = new SetRecordEntity();
        entity.setId(setRecord.getId());
        entity.setSessionId(setRecord.getSessionId());
        entity.setExerciseId(Long.parseLong(setRecord.getExerciseId()));
        entity.setExerciseName(setRecord.getExerciseId());
        entity.setReps(setRecord.getReps());
        entity.setWeight(setRecord.getWeight());
        entity.setPerformedAt(setRecord.getPerformedAt());
        return entity;
    }

    private SetRecord toDomain(SetRecordEntity entity) {
        SetRecord setRecord = new SetRecord();
        setRecord.setId(entity.getId());
        setRecord.setSessionId(entity.getSessionId());
        setRecord.setExerciseId(String.valueOf(entity.getExerciseId()));
        setRecord.setExerciseId(entity.getExerciseName());
        setRecord.setReps(entity.getReps());
        setRecord.setWeight(entity.getWeight());
        setRecord.setPerformedAt(entity.getPerformedAt());
        return setRecord;
    }
}