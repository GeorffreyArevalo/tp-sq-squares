package com.pragmafood.talentpool.square.infrastructure.output.jpa.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
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
}
