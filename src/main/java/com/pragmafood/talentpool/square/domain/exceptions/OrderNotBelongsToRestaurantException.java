package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class OrderNotBelongsToRestaurantException extends SquareBusinessException {

    public OrderNotBelongsToRestaurantException(String message) {
        super(message, StatusCodeException.ORDER_NOT_BELONGS_TO_RESTAURANT, 409);
    }
}
