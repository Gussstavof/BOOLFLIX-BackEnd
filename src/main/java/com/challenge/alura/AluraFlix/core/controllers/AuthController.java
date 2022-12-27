package com.challenge.alura.AluraFlix.core.controllers;


import com.challenge.alura.AluraFlix.core.entities.tokens.TokenDto;
import com.challenge.alura.AluraFlix.core.entities.users.UserDto;
import com.challenge.alura.AluraFlix.core.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> auth(@Valid @RequestBody UserDto userDto) throws Exception {
        String token = tokenService.generateToken(
                authenticationManager.authenticate(
                        parseUsernamePassword(userDto)));
        return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }

    private UsernamePasswordAuthenticationToken parseUsernamePassword(UserDto userDto) {
        return new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
    }
}
