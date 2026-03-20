package com.pragmafood.talentpool.square.application.dtos.responses;

public record RestaurantResponse(

    Long id,
    String name,
    String nit,
    String address,
    String phone,
    String logoUrl,
    Long ownerId
    
){
}
