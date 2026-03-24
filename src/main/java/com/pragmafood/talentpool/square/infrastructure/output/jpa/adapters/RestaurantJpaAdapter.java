package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers.RestaurantEntityMapper;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements RestaurantPersistencePort {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        RestaurantEntity savedEntity = restaurantRepository.save(entity);
        return restaurantEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantEntityMapper::toDomain);
    }
}
