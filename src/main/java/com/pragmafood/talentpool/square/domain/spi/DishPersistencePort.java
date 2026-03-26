package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;

import java.util.List;
import java.util.Optional;

public interface DishPersistencePort {

    Dish saveDish(Dish dish);

    Optional<Dish> findById(Long id);

    List<Dish> findAllByIds(List<Long> ids);

    PaginatedResult<Dish> findDishesByRestaurantId(Long restaurantId, String category, int page, int size, String sortDirection);
}
