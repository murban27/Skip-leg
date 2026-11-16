package fitnessapp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, Long> {
}