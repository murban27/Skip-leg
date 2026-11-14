package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.User;
import fitnessapp.domain.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the domain {@link UserRepository} port on top of
 * Spring Data JPA.
 */
@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity saved = jpaRepository.save(toEntity(user));
        return toDomain(saved);
    }

    private static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        if (user.getId() != null) {
            try {
                entity.setId(Long.parseLong(user.getId()));
            } catch (NumberFormatException e) {
                // non-numeric domain id: leave null so the DB generates one
            }
        }
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }

    @Override
    public Optional<User> findById(String id) {
        try {
            return jpaRepository.findById(Long.parseLong(id)).map(UserRepositoryAdapter::toDomain);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserRepositoryAdapter::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    private static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(String.valueOf(entity.getId()));
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        return user;
    }
}
