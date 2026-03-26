package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.ToggleDishStatusRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.handlers.dish.DishHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final DishHandler dishHandler;

    @PostMapping
    public ResponseEntity<DishResponse> createDish(
            @Valid @RequestBody DishRequest dishRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dishHandler.createDish(dishRequest, ownerId));
    }

    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponse> updateDish(
            @PathVariable("dishId") Long dishId,
            @Valid @RequestBody UpdateDishRequest updateDishRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(dishHandler.updateDish(dishId, updateDishRequest, ownerId));
    }

    @PatchMapping("/{dishId}/status")
    public ResponseEntity<DishResponse> toggleDishStatus(
            @PathVariable("dishId") Long dishId,
            @Valid @RequestBody ToggleDishStatusRequest toggleDishStatusRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(dishHandler.toggleDishStatus(dishId, toggleDishStatusRequest.active(), ownerId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<PaginatedResponse<DishListItemResponse>> listDishesByRestaurant(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "size") int size,
            @RequestParam(defaultValue = "asc", value = "sortDirection") String sortDirection) {
        return ResponseEntity.ok(dishHandler.listDishesByRestaurant(restaurantId, category, page, size, sortDirection));
    }
}
