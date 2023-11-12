package com.challenge.alura.AluraFlix.dtos.users;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignInRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
}
