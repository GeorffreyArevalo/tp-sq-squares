package com.pragmafood.talentpool.square.application.handlers.employeerestaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.EmployeeRestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.EmployeeRestaurantResponse;
import com.pragmafood.talentpool.square.application.mappers.EmployeeRestaurantRequestMapper;
import com.pragmafood.talentpool.square.domain.api.EmployeeRestaurantServicePort;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeRestaurantHandlerImpl implements EmployeeRestaurantHandler {

    private final EmployeeRestaurantServicePort employeeRestaurantServicePort;
    private final EmployeeRestaurantRequestMapper employeeRestaurantRequestMapper;

    @Override
    @Transactional
    public EmployeeRestaurantResponse assignEmployeeToRestaurant(EmployeeRestaurantRequest request, Long ownerId) {
        EmployeeRestaurant employeeRestaurant = employeeRestaurantRequestMapper.toDomain(request);
        employeeRestaurant = employeeRestaurantServicePort.assignEmployeeToRestaurant(employeeRestaurant, ownerId);
        return employeeRestaurantRequestMapper.toResponse(employeeRestaurant);
    }
}
