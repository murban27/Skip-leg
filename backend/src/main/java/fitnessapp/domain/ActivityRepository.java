package fitnessapp.domain;

import java.util.List;
import java.util.Optional;

/**
 * Port for activity persistence. Implemented by the infrastructure layer.
 */
public interface ActivityRepository {

    Activity save(Activity activity);

    Optional<Activity> findById(String id);

    List<Activity> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}
