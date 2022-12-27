package com.challenge.alura.AluraFlix.core.services;

import com.challenge.alura.AluraFlix.core.entities.users.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${alura.jwt.expiration}")
    private String expirationTime;

    @Value("${alura.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("API da plataforma de curso")
                .setSubject(user.getId())
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
