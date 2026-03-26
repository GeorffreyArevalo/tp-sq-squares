package com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByClientIdAndStatusIn(Long clientId, List<OrderStatus> statuses);

    Page<OrderEntity> findByRestaurant_IdAndStatus(Long restaurantId, OrderStatus status, Pageable pageable);
}
