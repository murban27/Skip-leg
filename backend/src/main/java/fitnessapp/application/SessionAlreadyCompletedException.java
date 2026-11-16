package fitnessapp.application;

public class SessionAlreadyCompletedException extends RuntimeException {
    public SessionAlreadyCompletedException(String message) {
        super(message);
    }
}