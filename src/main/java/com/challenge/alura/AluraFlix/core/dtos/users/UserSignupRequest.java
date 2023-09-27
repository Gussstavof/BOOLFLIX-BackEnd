package com.challenge.alura.AluraFlix.core.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignupRequest {
    private String username;
    private String email;
    private String password;
}
