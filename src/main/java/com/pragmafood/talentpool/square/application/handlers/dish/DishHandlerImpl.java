package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.application.mappers.DishRequestMapper;
import com.pragmafood.talentpool.square.domain.api.DishServicePort;
import com.pragmafood.talentpool.square.domain.models.Dish;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DishHandlerImpl implements DishHandler {

    private final DishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;

    @Override
    @Transactional
    public DishResponse createDish(DishRequest dishRequest) {
        Dish dish = dishServicePort.createDish(dishRequestMapper.toDomain(dishRequest));
        return dishRequestMapper.toResponse(dish);
    }
}
