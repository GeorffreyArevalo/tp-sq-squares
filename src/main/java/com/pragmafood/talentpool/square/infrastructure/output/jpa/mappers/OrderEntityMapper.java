package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.OrderDish;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.OrderDishEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.OrderEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    @Mapping(target = "restaurant.id", source = "restaurantId")
    OrderEntity toEntity(Order order);

    @Mapping(target = "dish.id", source = "dishId")
    @Mapping(target = "order", ignore = true)
    OrderDishEntity toEntity(OrderDish orderDish);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    Order toDomain(OrderEntity orderEntity);

    @Mapping(target = "dishId", source = "dish.id")
    OrderDish toDomain(OrderDishEntity orderDishEntity);
}
