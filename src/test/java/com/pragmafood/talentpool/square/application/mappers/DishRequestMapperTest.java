package com.pragmafood.talentpool.square.application.mappers;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.domain.models.Dish;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class DishRequestMapperTest {
    private final DishRequestMapper mapper = Mappers.getMapper(DishRequestMapper.class);

    @Test
    void toDomain_nullRequest_returnsNull() {
        assertNull(mapper.toDomain(null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
