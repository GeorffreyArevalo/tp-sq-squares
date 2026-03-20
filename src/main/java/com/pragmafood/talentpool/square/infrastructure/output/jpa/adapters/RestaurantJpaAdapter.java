package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers.IRestaurantEntityMapper;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements RestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        RestaurantEntity savedEntity = restaurantRepository.save(entity);
        return restaurantEntityMapper.toDomain(savedEntity);
    }
}
