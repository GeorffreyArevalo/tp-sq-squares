package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

public interface OrderServicePort {

    Order createOrder(Order order);

    PaginatedResult<Order> listOrdersByStatus(Long employeeId, OrderStatus status, int page, int size);

    Order assignOrderToEmployee(Long orderId, Long employeeId);
}
