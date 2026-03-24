package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.Dish;

public interface DishServicePort {

    Dish createDish(Dish dish);

    Dish updateDish(Long dishId, Integer price, String description, Long ownerId);
}
