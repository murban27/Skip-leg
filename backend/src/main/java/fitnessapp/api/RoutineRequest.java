package fitnessapp.api;

import java.util.List;

public record RoutineRequest(
        String name,
        String type,
        List<RoutineEntryRequest> exercises
) {
}