package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
