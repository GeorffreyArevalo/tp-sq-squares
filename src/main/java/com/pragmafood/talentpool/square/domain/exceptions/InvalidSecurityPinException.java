package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class InvalidSecurityPinException extends SquareBusinessException {

    public InvalidSecurityPinException(String message) {
        super(message, StatusCodeException.INVALID_SECURITY_PIN, 400);
    }
}
