package com.pragmafood.talentpool.square.application.handlers.restaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.application.mappers.RestaurantRequestMapper;
import com.pragmafood.talentpool.square.domain.api.RestaurantServicePort;
import com.pragmafood.talentpool.square.domain.models.Restaurant;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements RestaurantHandler {

    private final RestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;

    @Override
    @Transactional
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant =  restaurantServicePort.createRestaurant(restaurantRequestMapper.toDomain(restaurantRequest));
        return restaurantRequestMapper.toResponse(restaurant);
    }
}
