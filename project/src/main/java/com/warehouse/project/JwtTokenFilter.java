package com.warehouse.project;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = extractToken(request); // Funkcja do wyciągnięcia tokena z nagłówka
        if (token != null) {
            try {
                Authentication auth = validateToken(token); // Funkcja do weryfikacji tokena
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // Token niepoprawny lub wygasły
                // Możesz obsłużyć to odpowiednim komunikatem lub kodem błędu
            }
        }
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Odcięcie "Bearer "
        }
        return null;
    }

    private Authentication validateToken(String token) throws Exception {
        // Tutaj powinieneś umieścić logikę weryfikacji tokena
        // W przypadku JWT, możesz użyć bibliotek do weryfikacji podpisu i odczytu zawartości
        // Jeśli token jest poprawny, możesz utworzyć obiekt Authentication z odpowiednimi danymi
        // oraz uprawnieniami i zwrócić go
        // W przeciwnym razie rzuć wyjątek, aby oznaczyć niepoprawny token
        // Przykład:
        // return new UsernamePasswordAuthenticationToken(username, null, authorities);
        throw new Exception("Niepoprawny token");
    }
}
