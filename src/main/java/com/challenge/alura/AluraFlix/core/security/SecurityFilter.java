package com.challenge.alura.AluraFlix.core.security;

import com.challenge.alura.AluraFlix.core.exception.CredentialsInvalidException;
import com.challenge.alura.AluraFlix.core.repositories.UserRepository;
import com.challenge.alura.AluraFlix.core.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
        return Optional.ofNullable(request.getHeader("Authorization"))
                .orElse(null);
    }

    private void checkToken(HttpServletRequest request){
       var token = getToken(request);
        if (token != null){
           var subject = tokenService.getSubject(token.replace("Bearer", "").trim());
           checkUser(subject);
        }
    }

    private void checkUser(String subject){
        var user = userRepository.findByUsername(subject)
                 .orElseThrow(() -> new CredentialsInvalidException("Invalid credentials"));
        SecurityContextHolder.getContext()
                .setAuthentication( new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities()));
    }

}
