package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

import java.util.Optional;

public interface DishPersistencePort {

    Dish saveDish(Dish dish);

    Optional<Dish> findById(Long id);

    PaginatedResult<Dish> findDishesByRestaurantId(Long restaurantId, String category, int page, int size, String sortDirection);
}
