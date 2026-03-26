package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.spi.OrderPersistencePort;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.OrderEntity;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers.OrderEntityMapper;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderPersistencePort {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        if (entity.getDishes() != null) {
            entity.getDishes().forEach(dish -> dish.setOrder(entity));
        }
        OrderEntity savedEntity = orderRepository.save(entity);
        return orderEntityMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByClientIdAndStatusIn(Long clientId, List<OrderStatus> statuses) {
        return orderRepository.existsByClientIdAndStatusIn(clientId, statuses);
    }

    @Override
    public PaginatedResult<Order> findOrdersByRestaurantIdAndStatus(Long restaurantId, OrderStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<OrderEntity> entityPage = orderRepository.findByRestaurant_IdAndStatus(restaurantId, status, pageable);

        List<Order> orders = entityPage.getContent().stream()
                .map(orderEntityMapper::toDomain)
                .toList();

        return new PaginatedResult<>(
                orders,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderEntityMapper::toDomain);
    }
}
