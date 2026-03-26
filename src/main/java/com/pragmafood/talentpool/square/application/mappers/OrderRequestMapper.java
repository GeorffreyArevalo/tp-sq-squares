package com.pragmafood.talentpool.square.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderDishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderDishResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.OrderDish;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper {

    Order toDomain(OrderRequest orderRequest);

    OrderDish toDomain(OrderDishRequest orderDishRequest);

    @Mapping(target = "status", source = "status", qualifiedByName = "orderStatusToString")
    OrderResponse toResponse(Order order);

    OrderDishResponse toResponse(OrderDish orderDish);

    @Named("orderStatusToString")
    default String orderStatusToString(OrderStatus status) {
        return status != null ? status.name() : null;
    }
}
