package com.pragmafood.talentpool.square.domain.spi;

import java.util.Optional;

import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;

public interface EmployeeRestaurantPersistencePort {

    EmployeeRestaurant saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant);

    Optional<EmployeeRestaurant> findByEmployeeId(Long employeeId);
}
