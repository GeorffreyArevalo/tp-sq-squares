package com.pragmafood.talentpool.square.application.dtos.requests;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(

    @NotNull(message = "The restaurant ID is required")
    Long restaurantId,

    @NotEmpty(message = "The order must contain at least one dish")
    @Valid
    List<OrderDishRequest> dishes

) {}
