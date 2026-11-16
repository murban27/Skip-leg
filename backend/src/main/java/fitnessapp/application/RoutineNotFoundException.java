package fitnessapp.application;

public class RoutineNotFoundException extends RuntimeException {
    public RoutineNotFoundException(String message) {
        super(message);
    }
}