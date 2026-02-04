package com.suyash.support_platform.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            @NonNull HttpServletResponse res,
            @NonNull FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        // ✅ check header exists and starts with Bearer
        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                // ✅ extract claims
                Claims claims = jwtUtil.extractClaims(token);

                String username = claims.getSubject();
                String role = claims.get("role", String.class);

                // ✅ convert role → Spring authority
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(authority)
                        );

                // ✅ set auth context
                SecurityContextHolder.getContext()
                        .setAuthentication(auth);

            } catch (Exception e) {
                // invalid token → clear context
                SecurityContextHolder.clearContext();
            }
        }

        // continue filter chain
        chain.doFilter(req, res);
    }
}
