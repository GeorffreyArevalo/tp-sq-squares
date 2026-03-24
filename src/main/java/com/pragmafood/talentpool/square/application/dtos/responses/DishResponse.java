package com.pragmafood.talentpool.square.application.dtos.responses;

public record DishResponse(

    Long id,
    String name,
    Integer price,
    String description,
    String imageUrl,
    String category,
    Boolean active,
    RestaurantResponse restaurant
    
){
}
