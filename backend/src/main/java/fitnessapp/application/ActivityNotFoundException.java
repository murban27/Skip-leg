package fitnessapp.application;

/**
 * Thrown when an activity with the given identifier does not exist.
 */
public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(String id) {
        super("Activity not found: " + id);
    }
}
