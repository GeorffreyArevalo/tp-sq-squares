package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderDishRequest(

    @NotNull(message = "The dish ID is required")
    Long dishId,

    @NotNull(message = "The quantity is required")
    @Positive(message = "The quantity must be a positive integer greater than 0")
    Integer quantity

) {}
