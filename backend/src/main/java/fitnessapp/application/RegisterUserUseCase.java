package fitnessapp.application;

import fitnessapp.domain.User;
import fitnessapp.domain.UserRepository;

/**
 * Mock registration use case. Kept behind this use-case boundary so a real
 * implementation (hashed passwords, persistence rules) can replace it later
 * without touching the API layer.
 */
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a mock user. The password is stored as-is (mock behavior).
     *
     * @throws UserAlreadyExistsException if the email is already registered
     */
    public void register(String email, String password) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Email and password are required");
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }
        userRepository.save(new User(null, email, password, "CLIENT"));
    }
}
