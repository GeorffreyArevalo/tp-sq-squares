package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class OrderEntityMapperTest {
    private final OrderEntityMapper mapper = Mappers.getMapper(OrderEntityMapper.class);

    @Test
    void toEntity_nullOrder_returnsNull() {
        assertNull(mapper.toEntity((Order) null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
