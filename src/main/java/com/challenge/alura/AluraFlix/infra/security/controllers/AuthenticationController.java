package com.challenge.alura.AluraFlix.infra.security.controllers;

import com.challenge.alura.AluraFlix.core.dtos.tokens.TokenResponse;
import com.challenge.alura.AluraFlix.core.entities.users.User;
import com.challenge.alura.AluraFlix.core.dtos.users.UserSignInRequest;
import com.challenge.alura.AluraFlix.core.dtos.users.UserSignupRequest;
import com.challenge.alura.AluraFlix.infra.security.services.AuthenticationService;
import com.challenge.alura.AluraFlix.infra.security.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
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
