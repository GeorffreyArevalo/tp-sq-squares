package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotPendingException extends SquareBusinessException {

    public OrderNotPendingException(String message) {
        super(message, StatusCodeException.ORDER_NOT_PENDING, 409);
    }
}
