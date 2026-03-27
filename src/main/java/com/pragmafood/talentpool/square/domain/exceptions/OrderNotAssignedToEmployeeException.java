package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotAssignedToEmployeeException extends SquareBusinessException {

    public OrderNotAssignedToEmployeeException(String message) {
        super(message, StatusCodeException.ORDER_NOT_ASSIGNED_TO_EMPLOYEE, 409);
    }
}
