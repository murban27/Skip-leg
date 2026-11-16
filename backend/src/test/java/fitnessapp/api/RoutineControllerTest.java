package fitnessapp.api;

import fitnessapp.application.*;
import fitnessapp.domain.Routine;
import fitnessapp.domain.RoutineEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoutineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RoutineControllerTest {

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
    public void testCreateRoutine() throws Exception {
        Routine routine = new Routine();
        routine.setId(1L);
        routine.setName("Upper Body");
        routine.setType("UPPER");
        routine.setEntries(List.of(new RoutineEntry()));

        when(createRoutineUseCase.create(any(), any(), any())).thenReturn(routine);

        mockMvc.perform(post("/api/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Upper Body\",\"type\":\"UPPER\",\"exercises\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Upper Body"))
                .andExpect(jsonPath("$.type").value("UPPER"));
    }

    @Test
    public void testListRoutines() throws Exception {
        Routine routine1 = new Routine();
        routine1.setId(1L);
        routine1.setName("Upper Body");
        routine1.setType("UPPER");

        Routine routine2 = new Routine();
        routine2.setId(2L);
        routine2.setName("Lower Body");
        routine2.setType("LOWER");

        when(listRoutinesUseCase.list()).thenReturn(List.of(routine1, routine2));

        mockMvc.perform(get("/api/routines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Upper Body"))
                .andExpect(jsonPath("$[0].type").value("UPPER"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Lower Body"))
                .andExpect(jsonPath("$[1].type").value("LOWER"));
    }

    @Test
    public void testGetRoutine() throws Exception {
        Routine routine = new Routine();
        routine.setId(1L);
        routine.setName("Upper Body");
        routine.setType("UPPER");

        when(getRoutineUseCase.get(anyLong())).thenReturn(routine);

        mockMvc.perform(get("/api/routines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Upper Body"))
                .andExpect(jsonPath("$.type").value("UPPER"));
    }

    @Test
    public void testUpdateRoutine() throws Exception {
        Routine routine = new Routine();
        routine.setId(1L);
        routine.setName("Updated Routine");
        routine.setType("FULLBODY");

        when(updateRoutineUseCase.update(anyLong(), any(), any(), any())).thenReturn(routine);

        mockMvc.perform(put("/api/routines/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Routine\",\"type\":\"FULLBODY\",\"exercises\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Routine"))
                .andExpect(jsonPath("$.type").value("FULLBODY"));
    }

    @Test
    public void testDeleteRoutine() throws Exception {
        mockMvc.perform(delete("/api/routines/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetUnknownRoutine() throws Exception {
        when(getRoutineUseCase.get(anyLong())).thenThrow(new RoutineNotFoundException("Routine not found"));

        mockMvc.perform(get("/api/routines/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateRoutineWithInvalidType() throws Exception {
        when(createRoutineUseCase.create(any(), any(), any())).thenThrow(new IllegalArgumentException("Invalid routine type"));

        mockMvc.perform(post("/api/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Invalid Routine\",\"type\":\"INVALID\",\"exercises\":[]}"))
                .andExpect(status().isBadRequest());
    }
}