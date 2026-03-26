package com.pragmafood.talentpool.square.application.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(

    Long id,
    Long clientId,
    Long restaurantId,
    List<OrderDishResponse> dishes,
    String status,
    LocalDateTime createdAt,
    Long assignedEmployeeId

) {}
