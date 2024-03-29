package com.challenge.alura.AluraFlix.controllers.filters;

import com.challenge.alura.AluraFlix.services.TokenService;
import com.challenge.alura.AluraFlix.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        checkToken(request);
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private void checkToken(HttpServletRequest request) {
        var token = getToken(request);
        if (token != null) {
            var subject = tokenService.getSubject(token.replace("Bearer", "").trim());
            checkUser(subject);
        }
    }

    private void checkUser(String subject) {
        var user = userRepository.findByEmail(subject)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                ));
    }
}