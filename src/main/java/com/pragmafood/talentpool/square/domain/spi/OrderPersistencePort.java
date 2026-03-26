package com.pragmafood.talentpool.square.domain.spi;

import java.util.List;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;

public interface OrderPersistencePort {

    Order saveOrder(Order order);

    boolean existsByClientIdAndStatusIn(Long clientId, List<OrderStatus> statuses);
}
