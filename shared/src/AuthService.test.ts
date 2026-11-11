// AuthService.test.ts
// Unit tests for AuthService using Jest.

import AuthService from './AuthService';

describe('AuthService', () => {
    let authService: AuthService;

    beforeEach(() => {
        authService = new AuthService();
    });

    it('should simulate login and return a JWT token', async () => {
        const email = 'user@example.com';
        const password = 'password123';
        const token = await authService.mockLogin(email, password);
        expect(token).toBe('mock-jwt-token');
    });
});