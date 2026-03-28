package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.RestaurantEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantEntityMapperTest {
    private final RestaurantEntityMapper mapper = Mappers.getMapper(RestaurantEntityMapper.class);

    @Test
    void toEntity_nullRestaurant_returnsNull() {
        assertNull(mapper.toEntity((Restaurant) null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
