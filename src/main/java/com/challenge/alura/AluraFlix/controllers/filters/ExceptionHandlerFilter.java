package com.challenge.alura.AluraFlix.controllers.filters;

import com.challenge.alura.AluraFlix.dtos.exceptions.ExceptionResponse;
import com.challenge.alura.AluraFlix.exception.BadRequestException;
import com.challenge.alura.AluraFlix.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerFilter {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exception(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(exception.getMessage())
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> exception(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(exception.getMessage())
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> exception(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionResponse(exception.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> exception(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(
                new ExceptionResponse(exception.getAllErrors().stream()
                        .map(objectError -> new StringBuilder()
                                .append(objectError.getDefaultMessage())
                        )
                        .collect(Collectors.toList()).toString())
        );
    }
}
