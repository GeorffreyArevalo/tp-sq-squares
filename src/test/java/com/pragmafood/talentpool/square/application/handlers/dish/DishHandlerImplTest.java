package com.pragmafood.talentpool.square.application.handlers.dish;

import com.pragmafood.talentpool.square.application.dtos.requests.DishRequest;
import com.pragmafood.talentpool.square.application.dtos.requests.UpdateDishRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.DishResponse;
import com.pragmafood.talentpool.square.application.mappers.DishRequestMapper;
import com.pragmafood.talentpool.square.domain.api.DishServicePort;
import com.pragmafood.talentpool.square.domain.models.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishHandlerImplTest {
    @Mock DishServicePort dishServicePort;
    @Mock DishRequestMapper dishRequestMapper;
    @InjectMocks DishHandlerImpl dishHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishHandler = new DishHandlerImpl(dishServicePort, dishRequestMapper);
    }

    @Test
    void createDish_validRequest_returnsResponse() {
        DishRequest req = mock(DishRequest.class);
        Dish dish = new Dish();
        DishResponse resp = new DishResponse(
                1L, "Pizza", 100, "desc", "img", "cat", true,
                new com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse(1L, "R", "NIT", "ADDR", "PHONE", "LOGO", 2L)
        );
        when(dishRequestMapper.toDomain(req)).thenReturn(dish);
        when(dishServicePort.createDish(dish)).thenReturn(dish);
        when(dishRequestMapper.toResponse(dish)).thenReturn(resp);
        DishResponse result = dishHandler.createDish(req, 1L);
        assertNotNull(result);
        assertEquals("Pizza", result.name());
        verify(dishServicePort).createDish(dish);
    }

    @Test
    void updateDish_validRequest_returnsResponse() {
        UpdateDishRequest req = mock(UpdateDishRequest.class);
        Dish dish = new Dish();
        DishResponse resp = new DishResponse(
                1L, "Pizza", 100, "desc", "img", "cat", true,
                new com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse(1L, "R", "NIT", "ADDR", "PHONE", "LOGO", 2L)
        );
        when(dishServicePort.updateDish(anyLong(), any(), any(), anyLong())).thenReturn(dish);
        when(dishRequestMapper.toResponse(dish)).thenReturn(resp);
        DishResponse result = dishHandler.updateDish(1L, req, 1L);
        assertNotNull(result);
        assertEquals("Pizza", result.name());
    }
    // ...más pruebas unitarias para otros métodos...
}
