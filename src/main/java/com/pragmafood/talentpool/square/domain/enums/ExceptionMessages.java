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
    USER_NOT_RESTAURANT_OWNER("The user is not the owner of this restaurant"),
    DISH_NOT_FOUND("The dish was not found"),
    ORDER_DISHES_REQUIRED("The order must contain at least one dish"),
    ORDER_RESTAURANT_ID_REQUIRED("The restaurant ID is required for the order"),
    ORDER_DISH_QUANTITY_INVALID("Each dish quantity must be a positive integer greater than 0"),
    ORDER_DISHES_NOT_SAME_RESTAURANT("All dishes must belong to the specified restaurant"),
    ORDER_DISH_NOT_FOUND("One or more dishes were not found"),
    ACTIVE_ORDER_EXISTS("The client already has an active order"),
    EMPLOYEE_ID_REQUIRED("The employee ID is required"),
    EMPLOYEE_RESTAURANT_ID_REQUIRED("The restaurant ID is required to assign an employee"),
    EMPLOYEE_RESTAURANT_NOT_FOUND("The employee is not associated with any restaurant"),
    ORDER_NOT_FOUND("The order was not found"),
    ORDER_NOT_PENDING("The order is not in pending status"),
    ORDER_NOT_BELONGS_TO_RESTAURANT("The order does not belong to the employee's restaurant"),
    ORDER_NOT_IN_PREPARATION("The order is not in preparation status"),
    ORDER_NOT_ASSIGNED_TO_EMPLOYEE("The order is not assigned to this employee");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
