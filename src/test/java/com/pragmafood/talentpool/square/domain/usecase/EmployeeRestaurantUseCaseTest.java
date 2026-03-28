package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.EmployeeRestaurantPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRestaurantUseCaseTest {
            @Test
            void assignEmployeeToRestaurant_nullRestaurantId_throwsException() {
                EmployeeRestaurant er = new EmployeeRestaurant();
                er.setEmployeeId(1L);
                assertThrows(InvalidFieldsException.class, () -> employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L));
            }

            @Test
            void assignEmployeeToRestaurant_restaurantNotFound_throwsException() {
                EmployeeRestaurant er = new EmployeeRestaurant();
                er.setEmployeeId(1L);
                er.setRestaurantId(2L);
                when(restaurantPersistencePort.findById(2L)).thenReturn(Optional.empty());
                assertThrows(RestaurantNotFoundException.class, () -> employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L));
            }

            @Test
            void assignEmployeeToRestaurant_notOwner_throwsException() {
                EmployeeRestaurant er = new EmployeeRestaurant();
                er.setEmployeeId(1L);
                er.setRestaurantId(2L);
                Restaurant restaurant = new Restaurant();
                restaurant.setId(2L);
                restaurant.setOwnerId(99L);
                when(restaurantPersistencePort.findById(2L)).thenReturn(Optional.of(restaurant));
                assertThrows(UserNotOwnerException.class, () -> employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L));
            }
        @Test
        void assignEmployeeToRestaurant_success() {
            EmployeeRestaurant er = new EmployeeRestaurant();
            er.setEmployeeId(1L);
            er.setRestaurantId(2L);
            Restaurant restaurant = new Restaurant();
            restaurant.setId(2L);
            restaurant.setOwnerId(3L);
            when(restaurantPersistencePort.findById(2L)).thenReturn(Optional.of(restaurant));
            when(employeeRestaurantPersistencePort.saveEmployeeRestaurant(any(EmployeeRestaurant.class))).thenReturn(er);
            EmployeeRestaurant saved = employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L);
            assertNotNull(saved);
            verify(employeeRestaurantPersistencePort).saveEmployeeRestaurant(er);
        }
    @Mock EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    @Mock RestaurantPersistencePort restaurantPersistencePort;
    @InjectMocks EmployeeRestaurantUseCase employeeRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeRestaurantUseCase = new EmployeeRestaurantUseCase(employeeRestaurantPersistencePort, restaurantPersistencePort);
    }

    @Test
    void assignEmployeeToRestaurant_valid_saves() {
        EmployeeRestaurant er = new EmployeeRestaurant();
        er.setEmployeeId(1L);
        er.setRestaurantId(2L);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setOwnerId(3L);
        when(restaurantPersistencePort.findById(2L)).thenReturn(Optional.of(restaurant));
        when(employeeRestaurantPersistencePort.saveEmployeeRestaurant(any(EmployeeRestaurant.class))).thenReturn(er);
        EmployeeRestaurant saved = employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L);
        assertNotNull(saved);
        verify(employeeRestaurantPersistencePort).saveEmployeeRestaurant(er);
    }

    @Test
    void assignEmployeeToRestaurant_nullEmployeeId_throwsException() {
        EmployeeRestaurant er = new EmployeeRestaurant();
        er.setRestaurantId(2L);
        assertThrows(InvalidFieldsException.class, () -> employeeRestaurantUseCase.assignEmployeeToRestaurant(er, 3L));
    }

    // ...más pruebas unitarias para otros métodos y validaciones...
}
