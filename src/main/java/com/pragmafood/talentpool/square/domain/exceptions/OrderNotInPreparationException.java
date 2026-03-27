package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotInPreparationException extends SquareBusinessException {

    public OrderNotInPreparationException(String message) {
        super(message, StatusCodeException.ORDER_NOT_IN_PREPARATION, 409);
    }
}
