package com.pragmafood.talentpool.square.application.mappers;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantRequestMapperTest {
    private final RestaurantRequestMapper mapper = Mappers.getMapper(RestaurantRequestMapper.class);

    @Test
    void toDomain_nullRequest_returnsNull() {
        assertNull(mapper.toDomain(null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
