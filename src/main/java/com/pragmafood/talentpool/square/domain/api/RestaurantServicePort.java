package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.models.Restaurant;

public interface RestaurantServicePort {

    Restaurant createRestaurant(Restaurant restaurant);

    PaginatedResult<Restaurant> listRestaurants(int page, int size, String sortDirection);
}
