package fitnessapp.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        
        if (token == null || !token.equals("Bearer mock-jwt-token")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or missing JWT token");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}