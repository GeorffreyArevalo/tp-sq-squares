package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.DishEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByRestaurantIdAndActiveTrue(Long restaurantId, Pageable pageable);

    Page<DishEntity> findByRestaurantIdAndCategoryAndActiveTrue(Long restaurantId, String category, Pageable pageable);
}
