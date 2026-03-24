package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.models.Restaurant;

import java.util.Optional;

public interface RestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);

    Optional<Restaurant> findById(Long id);

    PaginatedResult<Restaurant> findAllRestaurants(int page, int size, String sortDirection);
}
