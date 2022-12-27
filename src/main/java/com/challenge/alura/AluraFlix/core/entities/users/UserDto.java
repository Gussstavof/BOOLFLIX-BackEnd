package com.challenge.alura.AluraFlix.core.entities.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    @Email
    private String username;
    @NotBlank
    private String password;
}
