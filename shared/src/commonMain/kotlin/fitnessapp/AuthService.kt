package fitnessapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthService {
    suspend fun mockLogin(email: String, password: String): String {
        // Simulate JWT token generation
        return "mock-jwt-token"
    }
}