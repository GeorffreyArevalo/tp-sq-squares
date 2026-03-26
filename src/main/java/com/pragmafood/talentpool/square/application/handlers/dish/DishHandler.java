package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishListItemResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;

public interface DishHandler {

    DishResponse createDish(DishRequest dishRequest, Long ownerId);

    DishResponse updateDish(Long dishId, UpdateDishRequest updateDishRequest, Long ownerId);

    DishResponse toggleDishStatus(Long dishId, Boolean active, Long ownerId);

    PaginatedResponse<DishListItemResponse> listDishesByRestaurant(Long restaurantId, String category, int page, int size, String sortDirection);
}
