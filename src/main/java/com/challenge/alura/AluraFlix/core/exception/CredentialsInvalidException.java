package com.challenge.alura.AluraFlix.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsInvalidException extends RuntimeException{
    public CredentialsInvalidException(String message){
        super(message);
    }
}
