package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

public interface DishServicePort {

    Dish createDish(Dish dish);

    Dish updateDish(Long dishId, Integer price, String description, Long ownerId);

    Dish toggleDishStatus(Long dishId, Boolean active, Long ownerId);

    PaginatedResult<Dish> listDishesByRestaurant(Long restaurantId, String category, int page, int size, String sortDirection);
}
