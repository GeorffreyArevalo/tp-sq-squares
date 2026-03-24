package com.pragmafood.talentpool.square.domain.enums;

public enum ExceptionMessages {
    
    RESTAURANT_NAME_REQUIRED("El nombre del restaurante es obligatorio"),
    RESTAURANT_NAME_ONLY_NUMBERS("El nombre del restaurante no puede contener solo números"),
    NIT_REQUIRED("El NIT es obligatorio"),
    NIT_ONLY_NUMBERS("El NIT debe ser únicamente numérico"),
    PHONE_REQUIRED("El teléfono es obligatorio"),
    PHONE_MAX_LENGTH("El teléfono debe contener un máximo de %s caracteres"),
    PHONE_INVALID("El teléfono debe ser numérico y puede contener el símbolo +"),
    OWNER_ID_REQUIRED("El id del propietario es obligatorio"),
    USER_NOT_OWNER("El usuario no tiene el rol de propietario"),
    DISH_NAME_REQUIRED("The dish name is required"),
    DISH_PRICE_REQUIRED("The dish price is required"),
    DISH_PRICE_MUST_BE_POSITIVE("The dish price must be a positive integer greater than 0"),
    DISH_DESCRIPTION_REQUIRED("The dish description is required"),
    DISH_IMAGE_URL_REQUIRED("The image URL is required"),
    DISH_CATEGORY_REQUIRED("The category is required"),
    DISH_RESTAURANT_ID_REQUIRED("The restaurant ID is required"),
    RESTAURANT_NOT_FOUND("The restaurant was not found"),
    USER_NOT_RESTAURANT_OWNER("The user is not the owner of this restaurant");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
