package fitnessapp.api;

import fitnessapp.application.CreateExerciseUseCase;
import fitnessapp.application.ListExercisesUseCase;
import fitnessapp.domain.Exercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateExerciseUseCase createExerciseUseCase;

    @MockBean
    private ListExercisesUseCase listExercisesUseCase;

    @Test
    public void testCreateExercise() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Push-ups");
        exercise.setBodyParts(List.of("CHEST", "ARMS"));

        when(createExerciseUseCase.create(any(), any(), any())).thenReturn(exercise);

        mockMvc.perform(post("/api/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Push-ups\",\"bodyParts\":[\"CHEST\",\"ARMS\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Push-ups"))
                .andExpect(jsonPath("$.bodyParts[0]").value("CHEST"))
                .andExpect(jsonPath("$.bodyParts[1]").value("ARMS"));
    }

    @Test
    public void testListExercises() throws Exception {
        Exercise exercise1 = new Exercise();
        exercise1.setId(1L);
        exercise1.setName("Push-ups");
        exercise1.setBodyParts(List.of("CHEST", "ARMS"));

        Exercise exercise2 = new Exercise();
        exercise2.setId(2L);
        exercise2.setName("Squats");
        exercise2.setBodyParts(List.of("LEGS"));

        when(listExercisesUseCase.list()).thenReturn(List.of(exercise1, exercise2));

        mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Push-ups"))
                .andExpect(jsonPath("$[0].bodyParts[0]").value("CHEST"))
                .andExpect(jsonPath("$[0].bodyParts[1]").value("ARMS"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Squats"))
                .andExpect(jsonPath("$[1].bodyParts[0]").value("LEGS"));
    }
}