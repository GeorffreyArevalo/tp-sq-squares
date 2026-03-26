package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class ActiveOrderExistsException extends SquareBusinessException {

    public ActiveOrderExistsException(String message) {
        super(message, StatusCodeException.ACTIVE_ORDER_EXISTS, 409);
    }
}
