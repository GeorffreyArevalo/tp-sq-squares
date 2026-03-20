package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
