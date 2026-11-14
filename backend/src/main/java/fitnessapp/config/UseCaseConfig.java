package fitnessapp.config;

import fitnessapp.application.DeleteActivityUseCase;
import fitnessapp.application.ListActivitiesUseCase;
import fitnessapp.application.LogActivityUseCase;
import fitnessapp.application.LoginUserUseCase;
import fitnessapp.application.RegisterUserUseCase;
import fitnessapp.application.UpdateActivityUseCase;
import fitnessapp.domain.ActivityRepository;
import fitnessapp.domain.UserRepository;
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
}
