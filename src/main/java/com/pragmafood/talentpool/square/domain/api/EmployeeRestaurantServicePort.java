package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;

public interface EmployeeRestaurantServicePort {

    EmployeeRestaurant assignEmployeeToRestaurant(EmployeeRestaurant employeeRestaurant, Long ownerId);
}
