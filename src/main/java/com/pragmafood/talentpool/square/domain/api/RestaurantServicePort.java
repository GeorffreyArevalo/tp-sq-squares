package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.Restaurant;

public interface RestaurantServicePort {

    Restaurant createRestaurant(Restaurant restaurant);
}
