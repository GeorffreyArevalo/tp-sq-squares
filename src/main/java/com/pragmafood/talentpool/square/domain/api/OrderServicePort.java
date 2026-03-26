package com.pragmafood.talentpool.square.domain.api;

import com.pragmafood.talentpool.square.domain.models.Order;

public interface OrderServicePort {

    Order createOrder(Order order);
}
