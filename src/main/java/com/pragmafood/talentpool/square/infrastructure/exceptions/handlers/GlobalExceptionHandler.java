package com.pragmafood.talentpool.square.infrastructure.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pragmafood.talentpool.square.domain.exceptions.SquareBusinessException;
import com.pragmafood.talentpool.square.infrastructure.exceptions.models.ExceptionResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ExceptionResponse<Map<String, String>> response = ExceptionResponse.<Map<String, String>>builder()
                .timestamp(LocalDateTime.now())
                .message("Validation failed")
                .details(errors)
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(SquareBusinessException.class)
    public ResponseEntity<ExceptionResponse<String>> handleSquareBusinessException(SquareBusinessException ex) {
        ExceptionResponse<String> response = ExceptionResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details("Ha ocurrido un error al procesar la solicitud del usuario")
                .statusCode(ex.getStatusCode().toString())
                .httpStatus(ex.getCode())
                .build();
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(response);
    }
}
