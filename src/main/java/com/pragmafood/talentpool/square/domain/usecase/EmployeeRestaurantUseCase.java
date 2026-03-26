package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.api.EmployeeRestaurantServicePort;
import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.EmployeeRestaurantPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;

public class EmployeeRestaurantUseCase implements EmployeeRestaurantServicePort {

    private final EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public EmployeeRestaurantUseCase(EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort,
                                     RestaurantPersistencePort restaurantPersistencePort) {
        this.employeeRestaurantPersistencePort = employeeRestaurantPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public EmployeeRestaurant assignEmployeeToRestaurant(EmployeeRestaurant employeeRestaurant, Long ownerId) {
        if (employeeRestaurant.getEmployeeId() == null) {
            throw new InvalidFieldsException(ExceptionMessages.EMPLOYEE_ID_REQUIRED.getMessage());
        }
        if (employeeRestaurant.getRestaurantId() == null) {
            throw new InvalidFieldsException(ExceptionMessages.EMPLOYEE_RESTAURANT_ID_REQUIRED.getMessage());
        }

        Restaurant restaurant = restaurantPersistencePort.findById(employeeRestaurant.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND.getMessage()));

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new UserNotOwnerException(ExceptionMessages.USER_NOT_RESTAURANT_OWNER.getMessage());
        }

        return employeeRestaurantPersistencePort.saveEmployeeRestaurant(employeeRestaurant);
    }
}
