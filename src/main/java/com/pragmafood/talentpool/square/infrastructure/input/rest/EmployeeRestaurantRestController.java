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

@RestController
@RequestMapping("/employee-restaurant")
@RequiredArgsConstructor
public class EmployeeRestaurantRestController {

    private final EmployeeRestaurantHandler employeeRestaurantHandler;

    @PostMapping
    public ResponseEntity<EmployeeRestaurantResponse> assignEmployeeToRestaurant(
            @Valid @RequestBody EmployeeRestaurantRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeRestaurantHandler.assignEmployeeToRestaurant(request, ownerId));
    }
}
