package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class InvalidFieldsException extends SquareBusinessException {

    public InvalidFieldsException(String message) {
        super(message, StatusCodeException.INVALID_FIELDS, 400);
    }
    
}
