package com.challenge.alura.AluraFlix.infra.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.challenge.alura.AluraFlix.core.dtos.tokens.TokenResponse;
import com.challenge.alura.AluraFlix.core.entities.users.User;
import com.challenge.alura.AluraFlix.core.dtos.users.UserSignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${alura.jwt.expiration}")
    private Long expirationTime;
    @Value("${alura.jwt.key}")
    private String key;
    @Autowired
    AuthenticationManager authenticationManager;

    public TokenResponse getToken(UserSignInRequest userSignInRequest) {
        return new TokenResponse(
                generateToken(
                        authenticationManager.authenticate(
                                parseUsernamePassword(userSignInRequest)
                        )
                )
        );
    }

    public String getSubject(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("aluraFlix")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new BadCredentialsException("Invalid Token");
        }
    }

    private String generateToken(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("aluraFlix")
                    .withExpiresAt(Instant.now().plusMillis(expirationTime))
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new BadCredentialsException("Invalid Token");
        }
    }

    private UsernamePasswordAuthenticationToken parseUsernamePassword(UserSignInRequest userSignInRequest) {
        return new UsernamePasswordAuthenticationToken(userSignInRequest.getEmail(), userSignInRequest.getPassword());
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(key);
    }
}
