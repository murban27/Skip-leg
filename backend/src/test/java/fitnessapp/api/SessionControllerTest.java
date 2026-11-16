package fitnessapp.api;

import fitnessapp.application.*;
import fitnessapp.domain.SetRecord;
import fitnessapp.domain.WorkoutSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartSessionUseCase startSessionUseCase;

    @MockBean
    private GetSessionUseCase getSessionUseCase;

    @MockBean
    private CompleteSessionUseCase completeSessionUseCase;

    @MockBean
    private RecordSetUseCase recordSetUseCase;

    @MockBean
    private ListSessionSetsUseCase listSessionSetsUseCase;

    @MockBean
    private ListSessionsUseCase listSessionsUseCase;

    @Test
    public void testStartSession() throws Exception {
        WorkoutSession session = new WorkoutSession();
        session.setId(1L);
        session.setStatus("IN_PROGRESS");
        session.setStartedAt(LocalDateTime.now());

        when(startSessionUseCase.start(anyLong(), any())).thenReturn(session);

        mockMvc.perform(post("/api/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"routineId\":1,\"overrides\":{}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    public void testRecordSet() throws Exception {
        SetRecord setRecord = new SetRecord();
        setRecord.setId(1L);
        setRecord.setReps(10);
        setRecord.setWeight(50.0);

        when(recordSetUseCase.record(anyLong(), any(), anyInt(), anyDouble())).thenReturn(setRecord);

        mockMvc.perform(post("/api/sessions/1/sets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reps\":10,\"weight\":50.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reps").value(10))
                .andExpect(jsonPath("$.weight").value(50.0));
    }

    @Test
    public void testListSessionSets() throws Exception {
        SetRecord setRecord1 = new SetRecord();
        setRecord1.setId(1L);
        setRecord1.setReps(10);
        setRecord1.setWeight(50.0);

        SetRecord setRecord2 = new SetRecord();
        setRecord2.setId(2L);
        setRecord2.setReps(8);
        setRecord2.setWeight(60.0);

        when(listSessionSetsUseCase.list(anyLong())).thenReturn(List.of(setRecord1, setRecord2));

        mockMvc.perform(get("/api/sessions/1/sets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].reps").value(10))
                .andExpect(jsonPath("$[0].weight").value(50.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].reps").value(8))
                .andExpect(jsonPath("$[1].weight").value(60.0));
    }

    @Test
    public void testCompleteSession() throws Exception {
        WorkoutSession session = new WorkoutSession();
        session.setId(1L);
        session.setStatus("COMPLETED");
        session.setCompletedAt(LocalDateTime.now());

        when(completeSessionUseCase.complete(anyLong())).thenReturn(session);

        mockMvc.perform(post("/api/sessions/1/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("COMPLETED"))
                .andExpect(jsonPath("$.completedAt").exists());
    }

    @Test
    public void testCompleteAlreadyCompletedSession() throws Exception {
        when(completeSessionUseCase.complete(anyLong())).thenThrow(new SessionAlreadyCompletedException("Session already completed"));

        mockMvc.perform(post("/api/sessions/1/complete"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testGetUnknownSession() throws Exception {
        when(getSessionUseCase.get(anyLong())).thenThrow(new SessionNotFoundException("Session not found"));

        mockMvc.perform(get("/api/sessions/999"))
                .andExpect(status().isNotFound());
    }
}