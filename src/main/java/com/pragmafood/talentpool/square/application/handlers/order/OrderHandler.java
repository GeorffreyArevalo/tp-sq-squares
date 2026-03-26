package com.pragmafood.talentpool.square.application.handlers.order;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;

public interface OrderHandler {

    OrderResponse createOrder(OrderRequest orderRequest, Long clientId);
}
