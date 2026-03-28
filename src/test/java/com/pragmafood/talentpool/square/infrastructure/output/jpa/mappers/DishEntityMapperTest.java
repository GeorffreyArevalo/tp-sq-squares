package com.pragmafood.talentpool.square.infrastructure.output.jpa.mappers;

import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.infrastructure.output.jpa.entities.DishEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class DishEntityMapperTest {
    private final DishEntityMapper mapper = Mappers.getMapper(DishEntityMapper.class);

    @Test
    void toEntity_nullDish_returnsNull() {
        assertNull(mapper.toEntity((Dish) null));
    }
    // ...más pruebas unitarias para métodos de mapeo...
}
