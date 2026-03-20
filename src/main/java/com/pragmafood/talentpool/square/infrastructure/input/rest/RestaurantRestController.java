package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.application.handlers.restaurant.RestaurantHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final RestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    restaurantHandler.createRestaurant(restaurantRequest)
                );
    }
}
