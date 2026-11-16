package fitnessapp.api;

import fitnessapp.application.*;
import fitnessapp.domain.SetRecord;
import fitnessapp.domain.WorkoutSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final StartSessionUseCase startSessionUseCase;
    private final CompleteSessionUseCase completeSessionUseCase;
    private final RecordSetUseCase recordSetUseCase;
    private final ListSessionSetsUseCase listSessionSetsUseCase;
    private final ListSessionsUseCase listSessionsUseCase;
    private final GetSessionUseCase getSessionUseCase;

    public SessionController(StartSessionUseCase startSessionUseCase,
                            CompleteSessionUseCase completeSessionUseCase,
                            RecordSetUseCase recordSetUseCase,
                            ListSessionSetsUseCase listSessionSetsUseCase,
                            ListSessionsUseCase listSessionsUseCase,
                            GetSessionUseCase getSessionUseCase) {
        this.startSessionUseCase = startSessionUseCase;
        this.completeSessionUseCase = completeSessionUseCase;
        this.recordSetUseCase = recordSetUseCase;
        this.listSessionSetsUseCase = listSessionSetsUseCase;
        this.listSessionsUseCase = listSessionsUseCase;
        this.getSessionUseCase = getSessionUseCase;
    }

    @PostMapping
    public ResponseEntity<SessionResponse> startSession(@RequestBody StartSessionRequest request) {
        WorkoutSession session = startSessionUseCase.start(request.routineId(), request.overrides());
        return ResponseEntity.status(HttpStatus.CREATED).body(SessionResponse.from(session));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> listSessions() {
        List<WorkoutSession> sessions = listSessionsUseCase.list();
        List<SessionResponse> responses = sessions.stream()
                .map(SessionResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSession(@PathVariable Long id) {
        WorkoutSession session = getSessionUseCase.get(id);
        return ResponseEntity.ok(SessionResponse.from(session));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<SessionResponse> completeSession(@PathVariable Long id) {
        WorkoutSession session = completeSessionUseCase.complete(id);
        return ResponseEntity.ok(SessionResponse.from(session));
    }

    @PostMapping("/{id}/sets")
    public ResponseEntity<SetRecordResponse> recordSet(
            @PathVariable Long id,
            @RequestBody SetRecordRequest request) {
        SetRecord setRecord = recordSetUseCase.record(id, null, request.reps(), request.weight());
        return ResponseEntity.status(HttpStatus.CREATED).body(SetRecordResponse.from(setRecord));
    }

    @GetMapping("/{id}/sets")
    public ResponseEntity<List<SetRecordResponse>> listSessionSets(@PathVariable Long id) {
        List<SetRecord> setRecords = listSessionSetsUseCase.list(id);
        List<SetRecordResponse> responses = setRecords.stream()
                .map(SetRecordResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}