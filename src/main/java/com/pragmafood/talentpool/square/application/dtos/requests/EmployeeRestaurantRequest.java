package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record EmployeeRestaurantRequest(

    @NotNull(message = "The employee ID is required")
    Long employeeId,

    @NotNull(message = "The restaurant ID is required")
    Long restaurantId

) {}
