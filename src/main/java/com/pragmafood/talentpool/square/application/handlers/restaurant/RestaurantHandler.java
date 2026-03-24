package com.pragmafood.talentpool.square.application.handlers.restaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;

public interface RestaurantHandler {

    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);

    PaginatedResponse<RestaurantListItemResponse> listRestaurants(int page, int size, String sortDirection);
}
