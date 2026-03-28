package com.pragmafood.talentpool.square.application.mappers;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.models.Order;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class OrderRequestMapperTest {
    private final OrderRequestMapper mapper = Mappers.getMapper(OrderRequestMapper.class);

    @Test
    void orderStatusToString_returnsName() {
        assertEquals("PENDING", mapper.orderStatusToString(OrderStatus.PENDING));
        assertNull(mapper.orderStatusToString(null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
