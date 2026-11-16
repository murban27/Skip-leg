package fitnessapp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineJpaRepository extends JpaRepository<RoutineEntity, Long> {
}