package fitnessapp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRecordJpaRepository extends JpaRepository<SetRecordEntity, Long> {
    List<SetRecordEntity> findBySessionId(Long sessionId);
}