package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderAlreadyInPreparationException extends SquareBusinessException {

    public OrderAlreadyInPreparationException(String message) {
        super(message, StatusCodeException.ORDER_ALREADY_IN_PREPARATION, 409);
    }
}
