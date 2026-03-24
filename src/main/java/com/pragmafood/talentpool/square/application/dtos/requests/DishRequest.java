package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DishRequest(

    @NotBlank(message = "The dish name is required")
    String name,

    @NotNull(message = "The dish price is required")
    @Positive(message = "The dish price must be a positive number greater than 0")
    Integer price,

    @NotBlank(message = "The dish description is required")
    String description,

    @NotBlank(message = "The image URL is required")
    String imageUrl,

    @NotBlank(message = "The category is required")
    String category,

    @NotNull(message = "The restaurant ID is required")
    Long restaurantId,

    @NotNull(message = "The owner ID is required")
    Long ownerId
    
){
}
