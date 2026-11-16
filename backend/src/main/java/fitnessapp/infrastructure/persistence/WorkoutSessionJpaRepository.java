package fitnessapp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionJpaRepository extends JpaRepository<WorkoutSessionEntity, Long> {
}