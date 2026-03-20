package com.pragmafood.talentpool.square.domain.spi;

import com.pragmafood.talentpool.square.domain.models.Restaurant;

public interface RestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);
}
