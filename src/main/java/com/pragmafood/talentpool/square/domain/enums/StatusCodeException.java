package com.pragmafood.talentpool.square.domain.enums;

public enum StatusCodeException {
    
    USER_ALREADY_EXISTS("40-UEX"),
    USER_NOT_FOUND("40-UNF"),
    INVALID_PASSWORD("40-IPW"),
    INVALID_FIELDS("40-IFD"),
    USER_NOT_OWNER("40-UNO"),
    USER_UNDER_AGE("40-UUA"),
    RESTAURANT_NOT_FOUND("44-RNF");

    private final String code;

    StatusCodeException(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
