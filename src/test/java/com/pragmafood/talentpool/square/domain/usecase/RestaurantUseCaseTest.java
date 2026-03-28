package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.clients.UserClientPort;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class RestaurantUseCaseTest {
            @Test
            void listRestaurants_emptyResult_success() {
                when(restaurantPersistencePort.findAllRestaurants(0, 1, "ASC")).thenReturn(new PaginatedResult<>(List.of(), 0, 1, 0L, 0));
                PaginatedResult<Restaurant> result = restaurantUseCase.listRestaurants(0, 1, "ASC");
                assertNotNull(result);
                assertEquals(0, result.getContent().size());
            }
        @Test
        void listRestaurants_success() {
            PaginatedResult<Restaurant> paginated = new PaginatedResult<>(List.of(new Restaurant()), 0, 1, 1L, 1);
            when(restaurantPersistencePort.findAllRestaurants(0, 1, "ASC")).thenReturn(paginated);
            PaginatedResult<Restaurant> result = restaurantUseCase.listRestaurants(0, 1, "ASC");
            assertNotNull(result);
            assertEquals(1, result.getContent().size());
        }
    @Mock RestaurantPersistencePort restaurantPersistencePort;
    @Mock UserClientPort userClient;
    @InjectMocks RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userClient);
    }

    @Test
    void createRestaurant_validRestaurant_savesRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("R");
        restaurant.setNit("123");
        restaurant.setPhone("1234567890");
        restaurant.setOwnerId(1L);
        when(userClient.hasOwnerRole(1L)).thenReturn(true);
        when(restaurantPersistencePort.saveRestaurant(any(Restaurant.class))).thenReturn(restaurant);
        Restaurant saved = restaurantUseCase.createRestaurant(restaurant);
        assertNotNull(saved);
        verify(restaurantPersistencePort).saveRestaurant(restaurant);
    }

    @Test
    void createRestaurant_invalidName_throwsException() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("");
        assertThrows(InvalidFieldsException.class, () -> restaurantUseCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_notOwner_throwsException() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("R");
        restaurant.setNit("123");
        restaurant.setPhone("1234567890");
        restaurant.setOwnerId(1L);
        when(userClient.hasOwnerRole(1L)).thenReturn(false);
        assertThrows(UserNotOwnerException.class, () -> restaurantUseCase.createRestaurant(restaurant));
    }
    // ...más pruebas unitarias para otros métodos y validaciones...
}
