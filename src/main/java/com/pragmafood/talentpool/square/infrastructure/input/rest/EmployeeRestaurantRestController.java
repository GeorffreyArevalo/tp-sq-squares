package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.EmployeeRestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.EmployeeRestaurantResponse;
import com.pragmafood.talentpool.square.application.handlers.employeerestaurant.EmployeeRestaurantHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/employee-restaurant")
@RequiredArgsConstructor
@Tag(name = "Empleados de Restaurante", description = "Asignación de empleados a restaurantes")
public class EmployeeRestaurantRestController {

    private final EmployeeRestaurantHandler employeeRestaurantHandler;

    @Operation(
        summary = "Asignar empleado a restaurante",
        description = "Asigna un empleado a un restaurante.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = EmployeeRestaurantRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Empleado asignado", content = @Content(schema = @Schema(implementation = EmployeeRestaurantResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<EmployeeRestaurantResponse> assignEmployeeToRestaurant(
            @Valid @RequestBody EmployeeRestaurantRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeRestaurantHandler.assignEmployeeToRestaurant(request, ownerId));
    }
}
