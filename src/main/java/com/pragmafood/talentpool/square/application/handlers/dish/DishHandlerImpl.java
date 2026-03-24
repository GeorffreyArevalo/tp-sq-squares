package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
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
    public DishResponse createDish(DishRequest dishRequest, Long ownerId) {
        Dish dish = dishRequestMapper.toDomain(dishRequest);
        dish.setOwnerId(ownerId);
        dish = dishServicePort.createDish(dish);
        return dishRequestMapper.toResponse(dish);
    }

    @Override
    @Transactional
    public DishResponse updateDish(Long dishId, UpdateDishRequest updateDishRequest, Long ownerId) {
        Dish dish = dishServicePort.updateDish(
                dishId,
                updateDishRequest.price(),
                updateDishRequest.description(),
                ownerId
        );
        return dishRequestMapper.toResponse(dish);
    }
}
