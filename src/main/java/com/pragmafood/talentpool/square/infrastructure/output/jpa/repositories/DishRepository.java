package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
