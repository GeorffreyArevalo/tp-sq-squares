package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class UserNotOwnerException extends SquareBusinessException {

    public UserNotOwnerException(String message) {
        super(message, StatusCodeException.USER_NOT_OWNER, 403);
    }
    
}
