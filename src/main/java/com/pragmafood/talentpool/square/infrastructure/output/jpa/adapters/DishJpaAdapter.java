package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
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

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id)
                .map(dishEntityMapper::toDomain);
    }

    @Override
    public PaginatedResult<Dish> findDishesByRestaurantId(Long restaurantId, String category, int page, int size, String sortDirection) {
        Sort sort = Sort.by("desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<DishEntity> entityPage;
        if (category != null && !category.isBlank()) {
            entityPage = dishRepository.findByRestaurantIdAndCategoryAndActiveTrue(restaurantId, category, pageable);
        } else {
            entityPage = dishRepository.findByRestaurantIdAndActiveTrue(restaurantId, pageable);
        }

        List<Dish> dishes = entityPage.getContent().stream()
                .map(dishEntityMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                dishes,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }
}
