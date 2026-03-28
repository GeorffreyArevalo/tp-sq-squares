package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.application.handlers.restaurant.RestaurantHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

@Tag(name = "Restaurantes", description = "Operaciones relacionadas con restaurantes")
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final RestaurantHandler restaurantHandler;

    @Operation(
        summary = "Crear restaurante",
        description = "Crea un nuevo restaurante.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = RestaurantRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Restaurante creado", content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    restaurantHandler.createRestaurant(restaurantRequest)
                );
    }

    @Operation(
        summary = "Listar restaurantes",
        description = "Obtiene una lista paginada de restaurantes.",
        parameters = {
            @Parameter(name = "page", description = "Página", example = "0"),
            @Parameter(name = "size", description = "Tamaño de página", example = "10"),
            @Parameter(name = "sortDirection", description = "Dirección de ordenamiento", example = "asc")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes", content = @Content(schema = @Schema(implementation = PaginatedResponse.class)))
        }
    )
    @GetMapping
    public ResponseEntity<PaginatedResponse<RestaurantListItemResponse>> listRestaurants(
            @RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "size") int size,
            @RequestParam(defaultValue = "asc", value = "sortDirection") String sortDirection) {
        return ResponseEntity.ok(restaurantHandler.listRestaurants(page, size, sortDirection));
    }
}
