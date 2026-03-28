package com.pragmafood.talentpool.square.application.handlers.order;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.mappers.OrderRequestMapper;
import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderHandlerImplTest {
    @Mock OrderServicePort orderServicePort;
    @Mock OrderRequestMapper orderRequestMapper;
    @InjectMocks OrderHandlerImpl orderHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderHandler = new OrderHandlerImpl(orderServicePort, orderRequestMapper);
    }

    @Test
    void createOrder_validRequest_returnsResponse() {
        OrderRequest req = mock(OrderRequest.class);
        Order order = new Order();
        OrderResponse resp = new OrderResponse(
                1L, 2L, 3L,
                List.of(new com.pragmafood.talentpool.square.application.dtos.responses.OrderDishResponse(10L, 2)),
                "PENDING", java.time.LocalDateTime.now(), 4L, "1234"
        );
        when(orderRequestMapper.toDomain(req)).thenReturn(order);
        when(orderServicePort.createOrder(order)).thenReturn(order);
        when(orderRequestMapper.toResponse(order)).thenReturn(resp);
        OrderResponse result = orderHandler.createOrder(req, 1L);
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("PENDING", result.status());
        verify(orderServicePort).createOrder(order);
    }

    @Test
    void listOrdersByStatus_returnsPaginatedResponse() {
        PaginatedResult<Order> paginated = new PaginatedResult<>(List.of(new Order()), 0, 1, 1L, 1);
        OrderResponse resp = new OrderResponse(
                1L, 2L, 3L,
                List.of(new com.pragmafood.talentpool.square.application.dtos.responses.OrderDishResponse(10L, 2)),
                "PENDING", java.time.LocalDateTime.now(), 4L, "1234"
        );
        when(orderServicePort.listOrdersByStatus(anyLong(), any(OrderStatus.class), anyInt(), anyInt())).thenReturn(paginated);
        when(orderRequestMapper.toResponse(any(Order.class))).thenReturn(resp);
        var result = orderHandler.listOrdersByStatus(1L, "PENDING", 0, 1);
        assertNotNull(result);
        assertEquals(1, result.content().size());
        assertEquals("PENDING", result.content().get(0).status());
    }
    // ...más pruebas unitarias para otros métodos...
}
