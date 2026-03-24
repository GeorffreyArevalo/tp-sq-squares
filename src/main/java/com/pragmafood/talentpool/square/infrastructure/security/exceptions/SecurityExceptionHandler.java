package com.pragmafood.talentpool.square.infrastructure.security.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pragmafood.talentpool.square.infrastructure.exceptions.models.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ExceptionResponse<String> body = ExceptionResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .message("Authentication is required to access this resource")
                .details(authException.getMessage())
                .statusCode("401-UNAUTHORIZED")
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .build();

        writeResponse(response, HttpStatus.UNAUTHORIZED, body);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ExceptionResponse<String> body = ExceptionResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .message("You do not have permission to access this resource")
                .details(accessDeniedException.getMessage())
                .statusCode("403-FORBIDDEN")
                .httpStatus(HttpStatus.FORBIDDEN.value())
                .build();

        writeResponse(response, HttpStatus.FORBIDDEN, body);
    }

    private void writeResponse(HttpServletResponse response, HttpStatus status,
                               ExceptionResponse<String> body) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getOutputStream(), body);
    }
}
