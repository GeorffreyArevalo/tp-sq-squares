package com.pragmafood.talentpool.square.application.handlers.order;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.mappers.OrderRequestMapper;
import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.models.Order;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements OrderHandler {

    private final OrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, Long clientId) {
        Order order = orderRequestMapper.toDomain(orderRequest);
        order.setClientId(clientId);
        order = orderServicePort.createOrder(order);
        return orderRequestMapper.toResponse(order);
    }
}
