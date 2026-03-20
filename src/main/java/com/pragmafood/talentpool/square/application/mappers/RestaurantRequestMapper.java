package com.pragmafood.talentpool.square.application.mappers;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    Restaurant toDomain(RestaurantRequest restaurantRequest);

    RestaurantResponse toResponse(Restaurant restaurant);
}
