package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotBelongsToClientException extends SquareBusinessException {

    public OrderNotBelongsToClientException(String message) {
        super(message, StatusCodeException.ORDER_NOT_BELONGS_TO_CLIENT, 409);
    }
}
