package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class DishNotFoundException extends SquareBusinessException {

    public DishNotFoundException(String message) {
        super(message, StatusCodeException.DISH_NOT_FOUND, 404);
    }
}
