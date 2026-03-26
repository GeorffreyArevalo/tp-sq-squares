package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class EmployeeRestaurantNotFoundException extends SquareBusinessException {

    public EmployeeRestaurantNotFoundException(String message) {
        super(message, StatusCodeException.EMPLOYEE_RESTAURANT_NOT_FOUND, 404);
    }
}
