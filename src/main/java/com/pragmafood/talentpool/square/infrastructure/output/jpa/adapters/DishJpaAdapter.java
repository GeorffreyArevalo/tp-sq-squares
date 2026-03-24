package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.spi.DishPersistencePort;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.DishEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers.DishEntityMapper;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories.DishRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements DishPersistencePort {

    private final DishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public Dish saveDish(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        DishEntity savedEntity = dishRepository.save(entity);
        return dishEntityMapper.toDomain(savedEntity);
    }
}
