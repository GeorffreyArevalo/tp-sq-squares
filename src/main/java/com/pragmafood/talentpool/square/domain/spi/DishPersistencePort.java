package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.Dish;

import java.util.Optional;

public interface DishPersistencePort {

    Dish saveDish(Dish dish);

    Optional<Dish> findById(Long id);
}
