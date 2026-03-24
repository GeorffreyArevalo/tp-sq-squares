package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class RestaurantNotFoundException extends SquareBusinessException {

    public RestaurantNotFoundException(String message) {
        super(message, StatusCodeException.RESTAURANT_NOT_FOUND, 404);
    }
    
}
