package fitnessapp.config;

import fitnessapp.application.*;
import fitnessapp.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Composition root: the only place where use cases are wired to their
 * repository ports. Swapping a persistence implementation changes only here.
 */
@Configuration
public class UseCaseConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository) {
        return new RegisterUserUseCase(userRepository);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(UserRepository userRepository) {
        return new LoginUserUseCase(userRepository);
    }

    @Bean
    public LogActivityUseCase logActivityUseCase(ActivityRepository activityRepository) {
        return new LogActivityUseCase(activityRepository);
    }

    @Bean
    public ListActivitiesUseCase listActivitiesUseCase(ActivityRepository activityRepository) {
        return new ListActivitiesUseCase(activityRepository);
    }

    @Bean
    public UpdateActivityUseCase updateActivityUseCase(ActivityRepository activityRepository) {
        return new UpdateActivityUseCase(activityRepository);
    }

    @Bean
    public DeleteActivityUseCase deleteActivityUseCase(ActivityRepository activityRepository) {
        return new DeleteActivityUseCase(activityRepository);
    }

    @Bean
    public CreateExerciseUseCase createExerciseUseCase(ExerciseRepository exerciseRepository) {
        return new CreateExerciseUseCase(exerciseRepository);
    }

    @Bean
    public ListExercisesUseCase listExercisesUseCase(ExerciseRepository exerciseRepository) {
        return new ListExercisesUseCase(exerciseRepository);
    }

    @Bean
    public CreateRoutineUseCase createRoutineUseCase(RoutineRepository routineRepository) {
        return new CreateRoutineUseCase(routineRepository);
    }

    @Bean
    public ListRoutinesUseCase listRoutinesUseCase(RoutineRepository routineRepository) {
        return new ListRoutinesUseCase(routineRepository);
    }

    @Bean
    public GetRoutineUseCase getRoutineUseCase(RoutineRepository routineRepository) {
        return new GetRoutineUseCase(routineRepository);
    }

    @Bean
    public DeleteRoutineUseCase deleteRoutineUseCase(RoutineRepository routineRepository) {
        return new DeleteRoutineUseCase(routineRepository);
    }

    @Bean
    public UpdateRoutineUseCase updateRoutineUseCase(RoutineRepository routineRepository) {
        return new UpdateRoutineUseCase(routineRepository);
    }

    @Bean
    public StartSessionUseCase startSessionUseCase(RoutineRepository routineRepository, WorkoutSessionRepository workoutSessionRepository) {
        return new StartSessionUseCase(routineRepository, workoutSessionRepository);
    }

    @Bean
    public CompleteSessionUseCase completeSessionUseCase(WorkoutSessionRepository workoutSessionRepository) {
        return new CompleteSessionUseCase(workoutSessionRepository);
    }

    @Bean
    public RecordSetUseCase recordSetUseCase(WorkoutSessionRepository workoutSessionRepository, SetRecordRepository setRecordRepository) {
        return new RecordSetUseCase(workoutSessionRepository, setRecordRepository);
    }

    @Bean
    public ListSessionSetsUseCase listSessionSetsUseCase(WorkoutSessionRepository workoutSessionRepository, SetRecordRepository setRecordRepository) {
        return new ListSessionSetsUseCase(workoutSessionRepository, setRecordRepository);
    }

    @Bean
    public ListSessionsUseCase listSessionsUseCase(WorkoutSessionRepository workoutSessionRepository) {
        return new ListSessionsUseCase(workoutSessionRepository);
    }

    @Bean
    public GetSessionUseCase getSessionUseCase(WorkoutSessionRepository workoutSessionRepository) {
        return new GetSessionUseCase(workoutSessionRepository);
    }
}
