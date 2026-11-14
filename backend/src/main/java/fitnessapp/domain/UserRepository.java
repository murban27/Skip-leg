package fitnessapp.domain;

import java.util.Optional;

/**
 * Port for user persistence. Implemented by the infrastructure layer.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
