package com.pragmafood.talentpool.square.application.dtos.responses;

public record DishListItemResponse(
    String name,
    Integer price,
    String description,
    String imageUrl,
    String category
) {
}
