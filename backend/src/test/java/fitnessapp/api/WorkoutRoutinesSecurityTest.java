package fitnessapp.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fitnessapp.application.CreateRoutineUseCase;
import fitnessapp.application.DeleteRoutineUseCase;
import fitnessapp.application.GetRoutineUseCase;
import fitnessapp.application.ListRoutinesUseCase;
import fitnessapp.application.UpdateRoutineUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Verifies that the routine endpoints require authentication: with the
 * security filters enabled and no bearer token, every request is rejected
 * with 401.
 */
@WebMvcTest(RoutineController.class)
@AutoConfigureMockMvc
class WorkoutRoutinesSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateRoutineUseCase createRoutineUseCase;

    @MockBean
    private ListRoutinesUseCase listRoutinesUseCase;

    @MockBean
    private GetRoutineUseCase getRoutineUseCase;

    @MockBean
    private UpdateRoutineUseCase updateRoutineUseCase;

    @MockBean
    private DeleteRoutineUseCase deleteRoutineUseCase;

    @Test
    void unauthenticatedListIsRejected() throws Exception {
        mockMvc.perform(get("/api/routines"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void unauthenticatedCreateIsRejected() throws Exception {
        // In this test slice the default security chain applies (the app's
        // CSRF-disabled SecurityConfig is not loaded), so a tokenless write
        // is rejected by CSRF protection with 403 before authentication.
        mockMvc.perform(post("/api/routines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Upper\",\"type\":\"UPPER\",\"exercises\":[]}"))
                .andExpect(status().isForbidden());
    }
}
