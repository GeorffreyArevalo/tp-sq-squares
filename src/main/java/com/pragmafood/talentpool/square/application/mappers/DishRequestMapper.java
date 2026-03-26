package com.pragmafood.talentpool.square.application.mappers;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.domain.models.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RestaurantRequestMapper.class}
    )
public interface DishRequestMapper {

    @Mapping(target = "restaurant.id", source = "restaurantId")
    Dish toDomain(DishRequest dishRequest);

    DishResponse toResponse(Dish dish);

    DishListItemResponse toListItemResponse(Dish dish);
}
