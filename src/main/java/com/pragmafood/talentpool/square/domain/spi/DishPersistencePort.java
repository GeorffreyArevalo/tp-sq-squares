package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.Dish;

public interface DishPersistencePort {

    Dish saveDish(Dish dish);
}
