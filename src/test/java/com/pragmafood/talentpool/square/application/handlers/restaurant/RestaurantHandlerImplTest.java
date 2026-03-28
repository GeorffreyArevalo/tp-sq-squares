package com.pragmafood.talentpool.square.application.handlers.restaurant;

import com.pragmafood.talentpool.square.application.dtos.requests.RestaurantRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.RestaurantResponse;
import com.pragmafood.talentpool.square.application.mappers.RestaurantRequestMapper;
import com.pragmafood.talentpool.square.domain.api.RestaurantServicePort;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantHandlerImplTest {
    @Mock RestaurantServicePort restaurantServicePort;
    @Mock RestaurantRequestMapper restaurantRequestMapper;
    @InjectMocks RestaurantHandlerImpl restaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantHandler = new RestaurantHandlerImpl(restaurantServicePort, restaurantRequestMapper);
    }

    @Test
    void createRestaurant_validRequest_returnsResponse() {
        RestaurantRequest req = mock(RestaurantRequest.class);
        Restaurant restaurant = new Restaurant();
        RestaurantResponse resp = new RestaurantResponse(
                1L, "R", "NIT", "ADDR", "PHONE", "LOGO", 2L
        );
        when(restaurantRequestMapper.toDomain(req)).thenReturn(restaurant);
        when(restaurantServicePort.createRestaurant(restaurant)).thenReturn(restaurant);
        when(restaurantRequestMapper.toResponse(restaurant)).thenReturn(resp);
        RestaurantResponse result = restaurantHandler.createRestaurant(req);
        assertNotNull(result);
        assertEquals("R", result.name());
        verify(restaurantServicePort).createRestaurant(restaurant);
    }
    // ...más pruebas unitarias para otros métodos...
}
