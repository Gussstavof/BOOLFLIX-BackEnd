package com.challenge.alura.AluraFlix.core.entities.users;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    @Email
    private String username;
    @NotBlank
    private String password;
}
