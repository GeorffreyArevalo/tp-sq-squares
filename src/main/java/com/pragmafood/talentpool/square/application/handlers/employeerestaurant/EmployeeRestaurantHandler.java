package com.pragmafood.talentpool.square.application.handlers.employeerestaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.EmployeeRestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.EmployeeRestaurantResponse;

public interface EmployeeRestaurantHandler {

    EmployeeRestaurantResponse assignEmployeeToRestaurant(EmployeeRestaurantRequest request, Long ownerId);
}
