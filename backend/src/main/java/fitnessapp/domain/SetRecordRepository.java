package fitnessapp.domain;

import java.util.List;

public interface SetRecordRepository {
    SetRecord save(SetRecord setRecord);
    List<SetRecord> findBySessionId(Long sessionId);
}