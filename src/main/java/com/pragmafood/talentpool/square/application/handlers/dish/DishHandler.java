package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;

public interface DishHandler {

    DishResponse createDish(DishRequest dishRequest, Long ownerId);

    DishResponse updateDish(Long dishId, UpdateDishRequest updateDishRequest, Long ownerId);
}
