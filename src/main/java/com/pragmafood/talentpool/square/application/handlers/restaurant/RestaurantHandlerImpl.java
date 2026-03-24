package com.pragmafood.talentpool.square.application.handlers.restaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.application.mappers.RestaurantRequestMapper;
import com.pragmafood.talentpool.square.domain.api.RestaurantServicePort;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.models.Restaurant;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<RestaurantListItemResponse> listRestaurants(int page, int size, String sortDirection) {
        PaginatedResult<Restaurant> result = restaurantServicePort.listRestaurants(page, size, sortDirection);

        List<RestaurantListItemResponse> items = result.getContent().stream()
                .map(restaurantRequestMapper::toListItemResponse)
                .toList();

        return new PaginatedResponse<>(
                items,
                result.getPage(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
