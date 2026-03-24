package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.api.DishServicePort;
import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.DishPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;

public class DishUseCase implements DishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(DishPersistencePort dishPersistencePort, RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Dish createDish(Dish dish) {
        validateName(dish.getName());
        validatePrice(dish.getPrice());
        validateDescription(dish.getDescription());
        validateImageUrl(dish.getImageUrl());
        validateCategory(dish.getCategory());
        validateRestaurantId(dish.getRestaurant().getId());

        Restaurant restaurant = restaurantPersistencePort.findById(dish.getRestaurant().getId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND.getMessage()));

        validateOwnership(restaurant.getOwnerId(), dish.getOwnerId());

        dish.setActive(true);

        return dishPersistencePort.saveDish(dish);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_NAME_REQUIRED.getMessage());
        }
    }

    private void validatePrice(Integer price) {
        if (price == null) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_PRICE_REQUIRED.getMessage());
        }
        if (price <= 0) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_PRICE_MUST_BE_POSITIVE.getMessage());
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_DESCRIPTION_REQUIRED.getMessage());
        }
    }

    private void validateImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_IMAGE_URL_REQUIRED.getMessage());
        }
    }

    private void validateCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_CATEGORY_REQUIRED.getMessage());
        }
    }

    private void validateRestaurantId(Long restaurantId) {
        if (restaurantId == null) {
            throw new InvalidFieldsException(ExceptionMessages.DISH_RESTAURANT_ID_REQUIRED.getMessage());
        }
    }

    private void validateOwnership(Long restaurantOwnerId, Long requestOwnerId) {
        if (requestOwnerId == null || !restaurantOwnerId.equals(requestOwnerId)) {
            throw new UserNotOwnerException(ExceptionMessages.USER_NOT_RESTAURANT_OWNER.getMessage());
        }
    }
}
