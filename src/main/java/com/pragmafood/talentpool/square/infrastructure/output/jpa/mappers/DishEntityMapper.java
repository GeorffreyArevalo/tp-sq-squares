package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RestaurantEntityMapper.class}
    )
public interface DishEntityMapper {
    
    DishEntity toEntity(Dish dish);

    Dish toDomain(DishEntity dishEntity);
}
