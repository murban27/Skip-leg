package fitnessapp.application;

import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineRepository;
import fitnessapp.domain.SessionEntry;
import fitnessapp.domain.WorkoutSession;
import fitnessapp.domain.WorkoutSessionRepository;

import java.util.Map;

public class StartSessionUseCase {
    private final RoutineRepository routineRepository;
    private final WorkoutSessionRepository workoutSessionRepository;

    public StartSessionUseCase(RoutineRepository routineRepository, WorkoutSessionRepository workoutSessionRepository) {
        this.routineRepository = routineRepository;
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public WorkoutSession start(Long routineId, Map<String, Integer> setOverrides) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RoutineNotFoundException("Routine not found with id: " + routineId));

        WorkoutSession session = new WorkoutSession();
        session.setRoutineId(String.valueOf(routine.getId()));

        routine.getEntries().forEach(entry -> {
            SessionEntry sessionEntry = new SessionEntry();
            sessionEntry.setExerciseId(entry.getExerciseId());
            sessionEntry.setExerciseName(entry.getExerciseName());

            if (setOverrides != null && setOverrides.containsKey(entry.getExerciseId())) {
                sessionEntry.setDefaultSets(setOverrides.get(entry.getExerciseId()));
            } else {
                sessionEntry.setDefaultSets(entry.getDefaultSets());
            }

            session.getEntries().add(sessionEntry);
        });

        return workoutSessionRepository.save(session);
    }
}