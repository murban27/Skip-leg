package fitnessapp.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fitnessapp.domain.User;
import fitnessapp.domain.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegisterUserUseCaseTest {

    static class InMemoryUserRepository implements UserRepository {
        final Map<String, User> store = new HashMap<>();

        @Override
        public User save(User user) {
            store.put(user.getEmail(), user);
            return user;
        }

        @Override
        public Optional<User> findById(String id) {
            return Optional.empty();
        }

        @Override
        public Optional<User> findByEmail(String email) {
            return Optional.ofNullable(store.get(email));
        }

        @Override
        public boolean existsByEmail(String email) {
            return store.containsKey(email);
        }
    }

    @Test
    void registersNewUser() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        RegisterUserUseCase useCase = new RegisterUserUseCase(repository);

        useCase.register("user@example.com", "secret");

        assertEquals(1, repository.store.size());
        assertEquals("CLIENT", repository.store.get("user@example.com").getRole());
    }

    @Test
    void rejectsDuplicateEmail() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        RegisterUserUseCase useCase = new RegisterUserUseCase(repository);
        useCase.register("user@example.com", "secret");

        assertThrows(UserAlreadyExistsException.class, () -> useCase.register("user@example.com", "other"));
    }

    @Test
    void rejectsBlankCredentials() {
        RegisterUserUseCase useCase = new RegisterUserUseCase(new InMemoryUserRepository());
        assertThrows(IllegalArgumentException.class, () -> useCase.register("", "secret"));
        assertThrows(IllegalArgumentException.class, () -> useCase.register("user@example.com", null));
    }
}
