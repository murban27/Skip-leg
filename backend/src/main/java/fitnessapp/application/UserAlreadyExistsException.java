package fitnessapp.application;

/**
 * Thrown when registration is attempted for an email that already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("User already exists: " + email);
    }
}
