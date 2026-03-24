package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.application.handlers.dish.DishHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final DishHandler dishHandler;

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@Valid @RequestBody DishRequest dishRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    dishHandler.createDish(dishRequest)
                );
    }

    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponse> updateDish(
            @PathVariable("dishId") Long dishId,
            @Valid @RequestBody UpdateDishRequest updateDishRequest) {
        return ResponseEntity.ok(dishHandler.updateDish(dishId, updateDishRequest));
    }
}
