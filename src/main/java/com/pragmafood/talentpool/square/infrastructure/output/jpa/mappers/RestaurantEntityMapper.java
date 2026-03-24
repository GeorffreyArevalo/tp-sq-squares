package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toDomain(RestaurantEntity restaurantEntity);
}
