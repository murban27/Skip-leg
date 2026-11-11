// AuthService.ts
// Shared module for authentication in TypeScript.

class AuthService {
    async mockLogin(email: string, password: string): Promise<string> {
        // Simulate JWT token generation
        return "mock-jwt-token";
    }
}

export default AuthService;