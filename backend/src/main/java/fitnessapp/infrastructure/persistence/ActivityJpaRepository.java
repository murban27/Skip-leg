package fitnessapp.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for the {@code activities} table. Numeric ids only —
 * String/Long translation happens in the repository adapter.
 */
public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, Long> {

    Optional<ActivityEntity> findByUserId(Long userId);
}
