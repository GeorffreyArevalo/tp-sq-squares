package com.pragmafood.talentpool.square.application.handlers.order;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;

public interface OrderHandler {

    OrderResponse createOrder(OrderRequest orderRequest, Long clientId);

    PaginatedResponse<OrderResponse> listOrdersByStatus(Long employeeId, String status, int page, int size);

    OrderResponse assignOrder(Long orderId, Long employeeId);
}
