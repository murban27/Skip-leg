package fitnessapp.application;

import fitnessapp.domain.UserRepository;

/**
 * Mock login use case. Issues the fixed mock token accepted by the JWT filter.
 */
public class LoginUserUseCase {

    public static final String MOCK_TOKEN = "mock-jwt-token";

    private final UserRepository userRepository;

    public LoginUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Simulates login and returns the mock JWT token.
     *
     * @throws InvalidCredentialsException if the email is unknown or the password does not match
     */
    public String login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> MOCK_TOKEN)
                .orElseThrow(InvalidCredentialsException::new);
    }
}
