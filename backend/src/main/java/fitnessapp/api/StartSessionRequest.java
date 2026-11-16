package fitnessapp.api;

import java.util.Map;

public record StartSessionRequest(
        Long routineId,
        Map<String, Integer> overrides
) {
}