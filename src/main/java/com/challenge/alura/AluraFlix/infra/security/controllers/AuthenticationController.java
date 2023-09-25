package com.challenge.alura.AluraFlix.infra.security.controllers;


import com.challenge.alura.AluraFlix.core.entities.tokens.TokenDto;
import com.challenge.alura.AluraFlix.core.entities.users.User;
import com.challenge.alura.AluraFlix.core.entities.users.UserDto;
import com.challenge.alura.AluraFlix.core.entities.users.UserSignupRequest;
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
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(tokenService.getToken(userDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signup(@Valid @RequestBody UserSignupRequest request,
                                           URI uri
    ) {
        User user = authenticationService.createUser(request);
        return ResponseEntity
                .created(uri)
                .body(tokenService.getToken(new UserDto(user.getEmail(), request.getPassword())));
    }
}
