package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateDishRequest(
    @NotNull(message = "The dish price is required")
    @Positive(message = "The dish price must be a positive integer greater than 0")
    Integer price,
    
    @NotNull(message = "The dish description is required")
    String description

) {}

