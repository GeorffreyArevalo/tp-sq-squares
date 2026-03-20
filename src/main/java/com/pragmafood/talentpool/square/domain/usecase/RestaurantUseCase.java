package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.api.RestaurantServicePort;
import com.pragmafood.talentpool.square.domain.clients.UserClientPort;
import com.pragmafood.talentpool.square.domain.constants.RestaurantConstants;
import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;

public class RestaurantUseCase implements RestaurantServicePort {

    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserClientPort userClient;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort, UserClientPort userClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userClient = userClient;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        validateName(restaurant.getName());
        validateNit(restaurant.getNit());
        validatePhone(restaurant.getPhone());
        validateOwnerRole(restaurant.getOwnerId());

        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    private void validateName(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.RESTAURANT_NAME_REQUIRED.getMessage());
        }
        if (nombre.matches(RestaurantConstants.ONLY_NUMBERS_REGEX)) {
            throw new InvalidFieldsException(ExceptionMessages.RESTAURANT_NAME_ONLY_NUMBERS.getMessage());
        }
    }

    private void validateNit(String nit) {
        if (nit == null || nit.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.NIT_REQUIRED.getMessage());
        }
        if (!nit.matches(RestaurantConstants.NIT_REGEX)) {
            throw new InvalidFieldsException(ExceptionMessages.NIT_ONLY_NUMBERS.getMessage());
        }
    }

    private void validatePhone(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.PHONE_REQUIRED.getMessage());
        }
        if (telefono.length() > RestaurantConstants.MAX_PHONE_LENGTH) {
            throw new InvalidFieldsException( String.format(ExceptionMessages.PHONE_MAX_LENGTH.getMessage(), RestaurantConstants.MAX_PHONE_LENGTH) );
        }
        if (!telefono.matches(RestaurantConstants.PHONE_REGEX)) {
            throw new InvalidFieldsException(ExceptionMessages.PHONE_INVALID.getMessage());
        }
    }

    private void validateOwnerRole(Long idPropietario) {
        if (idPropietario == null) {
            throw new InvalidFieldsException(ExceptionMessages.OWNER_ID_REQUIRED.getMessage());
        }
        if (!userClient.hasOwnerRole(idPropietario)) {
            throw new UserNotOwnerException(ExceptionMessages.USER_NOT_OWNER.getMessage());
        }
    }
}
