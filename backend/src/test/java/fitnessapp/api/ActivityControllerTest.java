package fitnessapp.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fitnessapp.application.ActivityNotFoundException;
import fitnessapp.application.DeleteActivityUseCase;
import fitnessapp.application.ListActivitiesUseCase;
import fitnessapp.application.LogActivityUseCase;
import fitnessapp.application.UpdateActivityUseCase;
import fitnessapp.domain.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ActivityController.class)
@AutoConfigureMockMvc(addFilters = false)
class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogActivityUseCase logActivityUseCase;

    @MockBean
    private ListActivitiesUseCase listActivitiesUseCase;

    @MockBean
    private UpdateActivityUseCase updateActivityUseCase;

    @MockBean
    private DeleteActivityUseCase deleteActivityUseCase;

    @Test
    void logsActivity() throws Exception {
        Activity logged = new Activity("abc-123", "123", "Running", 30, "High");
        when(logActivityUseCase.log(any(Activity.class))).thenReturn(logged);

        mockMvc.perform(post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"123\",\"type\":\"Running\",\"duration\":30,\"intensity\":\"High\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("abc-123"))
                .andExpect(jsonPath("$.type").value("Running"))
                .andExpect(jsonPath("$.duration").value(30))
                .andExpect(jsonPath("$.intensity").value("High"));
    }

    @Test
    void listsActivities() throws Exception {
        when(listActivitiesUseCase.list())
                .thenReturn(java.util.List.of(new Activity("abc-123", "123", "Running", 30, "High")));

        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].type").value("Running"));
    }

    @Test
    void updatesActivity() throws Exception {
        Activity updated = new Activity("abc-123", "123", "Cycling", 45, "Medium");
        when(updateActivityUseCase.update(eq("abc-123"), any(Activity.class))).thenReturn(updated);

        mockMvc.perform(put("/api/activities/abc-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"123\",\"type\":\"Cycling\",\"duration\":45,\"intensity\":\"Medium\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Cycling"))
                .andExpect(jsonPath("$.duration").value(45));
    }

    @Test
    void deletesActivity() throws Exception {
        mockMvc.perform(delete("/api/activities/abc-123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void mapsUnknownActivityTo404() throws Exception {
        when(updateActivityUseCase.update(any(), any(Activity.class)))
                .thenThrow(new ActivityNotFoundException("missing"));

        mockMvc.perform(put("/api/activities/missing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"123\",\"type\":\"Cycling\",\"duration\":45,\"intensity\":\"Medium\"}"))
                .andExpect(status().isNotFound());
    }
}
