package fitnessapp.api;

import fitnessapp.application.LoginUserUseCase;
import fitnessapp.application.RegisterUserUseCase;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mock authentication endpoints. Serves the React Native mobile app.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/mock-register")
    public ResponseEntity<Map<String, String>> mockRegister(@Valid @RequestBody AuthRequest request) {
        registerUserUseCase.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Mock user registered successfully"));
    }

    @PostMapping("/mock-login")
    public ResponseEntity<Map<String, String>> mockLogin(@Valid @RequestBody LoginRequest request) {
        String token = loginUserUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
