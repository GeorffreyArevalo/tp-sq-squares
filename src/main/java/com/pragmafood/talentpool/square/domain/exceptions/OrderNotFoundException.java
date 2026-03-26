package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotFoundException extends SquareBusinessException {

    public OrderNotFoundException(String message) {
        super(message, StatusCodeException.ORDER_NOT_FOUND, 404);
    }
}
