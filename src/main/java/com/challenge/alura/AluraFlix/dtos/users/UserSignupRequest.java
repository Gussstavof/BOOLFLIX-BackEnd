package com.challenge.alura.AluraFlix.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignupRequest {
    @NotEmpty(message = "username: is empty")
    private String username;
    @NotEmpty(message = "email: is empty")
    @Email(message = "email: invalid")
    private String email;
    @NotEmpty(message = "password: is empty")
    private String password;
}
