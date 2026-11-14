package fitnessapp.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fitnessapp.domain.User;
import fitnessapp.domain.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LoginUserUseCaseTest {

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
    void returnsMockTokenForValidCredentials() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.save(new User(null, "user@example.com", "secret", "CLIENT"));
        LoginUserUseCase useCase = new LoginUserUseCase(repository);

        assertEquals(LoginUserUseCase.MOCK_TOKEN, useCase.login("user@example.com", "secret"));
    }

    @Test
    void rejectsUnknownEmail() {
        LoginUserUseCase useCase = new LoginUserUseCase(new InMemoryUserRepository());
        assertThrows(InvalidCredentialsException.class, () -> useCase.login("ghost@example.com", "secret"));
    }

    @Test
    void rejectsWrongPassword() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.save(new User(null, "user@example.com", "secret", "CLIENT"));
        LoginUserUseCase useCase = new LoginUserUseCase(repository);

        assertThrows(InvalidCredentialsException.class, () -> useCase.login("user@example.com", "wrong"));
    }
}
