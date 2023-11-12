package com.challenge.alura.AluraFlix.controllers;

import com.challenge.alura.AluraFlix.dtos.tokens.TokenResponse;
import com.challenge.alura.AluraFlix.core.entities.users.User;
import com.challenge.alura.AluraFlix.dtos.users.UserSignInRequest;
import com.challenge.alura.AluraFlix.dtos.users.UserSignupRequest;
import com.challenge.alura.AluraFlix.infra.security.services.AuthenticationService;
import com.challenge.alura.AluraFlix.infra.security.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody UserSignInRequest userSignInRequest) {
        return ResponseEntity.ok(tokenService.getToken(userSignInRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signup(
            @Valid @RequestBody UserSignupRequest request,
            URI uri
    ) {
        User user = authenticationService.createUser(request);
        return ResponseEntity
                .created(uri)
                .body(tokenService.getToken(new UserSignInRequest(user.getEmail(), request.getPassword())));
    }
}
