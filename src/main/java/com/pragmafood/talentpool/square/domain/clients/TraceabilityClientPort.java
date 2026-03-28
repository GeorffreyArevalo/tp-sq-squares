package com.pragmafood.talentpool.square.domain.clients;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;

public interface TraceabilityClientPort {

    void recordOrderStatusChange(Long orderId, Long clientId, OrderStatus previousStatus, OrderStatus newStatus,
                                  Long employeeId);
}
