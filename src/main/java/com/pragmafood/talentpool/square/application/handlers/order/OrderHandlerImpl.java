package com.pragmafood.talentpool.square.application.handlers.order;

import java.util.List;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.mappers.OrderRequestMapper;
import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

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

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<OrderResponse> listOrdersByStatus(Long employeeId, String status, int page, int size) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        PaginatedResult<Order> result = orderServicePort.listOrdersByStatus(employeeId, orderStatus, page, size);

        List<OrderResponse> items = result.getContent().stream()
                .map(orderRequestMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(
                items,
                result.getPage(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
