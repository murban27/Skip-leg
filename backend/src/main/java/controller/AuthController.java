package fitnessapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/mock-register")
    public Map<String, String> mockRegister(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Mock user registered successfully");
        return response;
    }

    @PostMapping("/mock-login")
    public Map<String, String> mockLogin(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("token", "mock-jwt-token");
        return response;
    }
}