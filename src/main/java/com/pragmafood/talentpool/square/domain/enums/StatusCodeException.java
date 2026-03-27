package com.pragmafood.talentpool.square.domain.enums;

public enum StatusCodeException {
    
    USER_ALREADY_EXISTS("40-UEX"),
    USER_NOT_FOUND("40-UNF"),
    INVALID_PASSWORD("40-IPW"),
    INVALID_FIELDS("40-IFD"),
    USER_NOT_OWNER("40-UNO"),
    USER_UNDER_AGE("40-UUA"),
    RESTAURANT_NOT_FOUND("44-RNF"),
    DISH_NOT_FOUND("44-DNF"),
    ACTIVE_ORDER_EXISTS("49-AOE"),
    EMPLOYEE_RESTAURANT_NOT_FOUND("44-ERNF"),
    ORDER_NOT_FOUND("44-ONF"),
    ORDER_NOT_PENDING("49-ONP"),
    ORDER_NOT_BELONGS_TO_RESTAURANT("49-ONBR"),
    ORDER_NOT_IN_PREPARATION("49-ONIP"),
    ORDER_NOT_ASSIGNED_TO_EMPLOYEE("49-ONATE");

    private final String code;

    StatusCodeException(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
