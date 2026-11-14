package fitnessapp.infrastructure.security;

import fitnessapp.application.LoginUserUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Validates the {@code Authorization: Bearer mock-jwt-token} header and grants
 * request-scoped authentication when it matches. Routes are then protected by
 * {@link SecurityConfig}'s authorization rules.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String MOCK_TOKEN = LoginUserUseCase.MOCK_TOKEN;

    private static final List<SimpleGrantedAuthority> MOCK_AUTHORITIES =
            List.of(new SimpleGrantedAuthority("ROLE_MOCK_USER"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            if (("Bearer " + MOCK_TOKEN).equals(authorization)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken("mock-user", MOCK_TOKEN, MOCK_AUTHORITIES);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
