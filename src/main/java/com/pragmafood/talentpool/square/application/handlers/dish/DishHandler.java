package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;

public interface DishHandler {

    DishResponse createDish(DishRequest dishRequest);
}
