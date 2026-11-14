package fitnessapp.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fitnessapp.domain.Activity;
import fitnessapp.domain.ActivityRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LogActivityUseCaseTest {

    static class InMemoryActivityRepository implements ActivityRepository {
        final Map<String, Activity> store = new HashMap<>();

        @Override
        public Activity save(Activity activity) {
            store.put(activity.getId(), activity);
            return activity;
        }

        @Override
        public Optional<Activity> findById(String id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Activity> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void deleteById(String id) {
            store.remove(id);
        }

        @Override
        public boolean existsById(String id) {
            return store.containsKey(id);
        }
    }

    @Test
    void assignsIdAndStoresActivity() {
        InMemoryActivityRepository repository = new InMemoryActivityRepository();
        LogActivityUseCase useCase = new LogActivityUseCase(repository);
        Activity activity = new Activity(null, "123", "Running", 30, "High");

        Activity logged = useCase.log(activity);

        assertNotNull(logged.getId());
        assertEquals(1, repository.store.size());
        assertEquals("Running", repository.store.get(logged.getId()).getType());
    }

    @Test
    void rejectsBlankType() {
        LogActivityUseCase useCase = new LogActivityUseCase(new InMemoryActivityRepository());
        assertThrows(IllegalArgumentException.class,
                () -> useCase.log(new Activity(null, "123", "", 30, "High")));
    }

    @Test
    void rejectsNonPositiveDuration() {
        LogActivityUseCase useCase = new LogActivityUseCase(new InMemoryActivityRepository());
        assertThrows(IllegalArgumentException.class,
                () -> useCase.log(new Activity(null, "123", "Running", 0, "High")));
    }
}
