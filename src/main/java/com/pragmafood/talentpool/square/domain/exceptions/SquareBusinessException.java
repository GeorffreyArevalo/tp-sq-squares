package com.pragmafood.talentpool.square.domain.exceptions;

import com.pragmafood.talentpool.square.domain.enums.StatusCodeException;

public class SquareBusinessException extends RuntimeException {

    private final int code;
    private final StatusCodeException statusCode;

    public SquareBusinessException(String message, StatusCodeException statusCode, int code) {
        super(message);
        this.statusCode = statusCode;
        this.code = code;
    }

    public StatusCodeException getStatusCode() {
        return statusCode;
    }

    public int getCode() {
        return code;

    }
}
