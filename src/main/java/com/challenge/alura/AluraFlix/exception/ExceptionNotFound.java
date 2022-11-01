package com.challenge.alura.AluraFlix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionNotFound extends RuntimeException {

    public ExceptionNotFound(String message) {
        super(message);
    }
}