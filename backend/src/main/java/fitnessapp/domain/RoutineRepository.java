package fitnessapp.domain;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository {
    Routine save(Routine routine);
    Optional<Routine> findById(Long id);
    List<Routine> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}