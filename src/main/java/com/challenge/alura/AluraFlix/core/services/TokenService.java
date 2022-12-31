package com.challenge.alura.AluraFlix.core.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.challenge.alura.AluraFlix.core.entities.users.User;
import com.challenge.alura.AluraFlix.core.exception.CredentialsInvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${alura.jwt.expiration}")
    private String expirationTime;

    @Value("${alura.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
           return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("aluraFlix")
                    .withExpiresAt(Instant.now().plusMillis(7200000L))
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception){
            throw new CredentialsInvalidException("Invalid Token");
        }
    }

    public String getSubject(String token){
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("aluraFlix")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException exception){
            throw new CredentialsInvalidException("Invalid Token");
        }
    }

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secret);
    }
}
