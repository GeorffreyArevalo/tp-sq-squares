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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Platos", description = "Gestión de platos")
@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final DishHandler dishHandler;

    @Operation(
        summary = "Crear plato",
        description = "Crea un nuevo plato.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = DishRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Plato creado", content = @Content(schema = @Schema(implementation = DishResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<DishResponse> createDish(
            @Valid @RequestBody DishRequest dishRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dishHandler.createDish(dishRequest, ownerId));
    }

    @Operation(
        summary = "Actualizar plato",
        description = "Actualiza la información de un plato.",
        parameters = {
            @Parameter(name = "dishId", description = "ID del plato")
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = UpdateDishRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Plato actualizado", content = @Content(schema = @Schema(implementation = DishResponse.class)))
        }
    )
    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponse> updateDish(
            @PathVariable("dishId") Long dishId,
            @Valid @RequestBody UpdateDishRequest updateDishRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long ownerId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(dishHandler.updateDish(dishId, updateDishRequest, ownerId));
    }

    @Operation(
        summary = "Cambiar estado del plato",
        description = "Activa o desactiva un plato.",
        parameters = {
            @Parameter(name = "dishId", description = "ID del plato")
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = ToggleDishStatusRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado", content = @Content(schema = @Schema(implementation = DishResponse.class)))
        }
    )
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
