package com.pragmafood.talentpool.square.domain.spi;

import java.util.List;
import java.util.Optional;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

public interface OrderPersistencePort {

    Order saveOrder(Order order);

    boolean existsByClientIdAndStatusIn(Long clientId, List<OrderStatus> statuses);

    PaginatedResult<Order> findOrdersByRestaurantIdAndStatus(Long restaurantId, OrderStatus status, int page, int size);

    Optional<Order> findById(Long orderId);
}
