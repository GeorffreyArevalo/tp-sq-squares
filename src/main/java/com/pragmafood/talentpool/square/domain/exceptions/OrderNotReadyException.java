package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotReadyException extends SquareBusinessException {

    public OrderNotReadyException(String message) {
        super(message, StatusCodeException.ORDER_NOT_READY, 409);
    }
}
