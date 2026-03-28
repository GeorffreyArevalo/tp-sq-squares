package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.UserNotOwnerException;
import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.models.Restaurant;
import com.pragmafood.talentpool.square.domain.spi.DishPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {
            @Test
            void updateDish_dishNotFound_throwsException() {
                when(dishPersistencePort.findById(1L)).thenReturn(Optional.empty());
                assertThrows(com.pragmafood.talentpool.square.domain.exceptions.DishNotFoundException.class,
                    () -> dishUseCase.updateDish(1L, 100, "desc", 2L));
            }

            @Test
            void updateDish_restaurantNotFound_throwsException() {
                Dish dish = new Dish();
                Restaurant rest = new Restaurant();
                rest.setId(1L);
                dish.setRestaurant(rest);
                when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(dish));
                when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());
                assertThrows(com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException.class,
                    () -> dishUseCase.updateDish(1L, 100, "desc", 2L));
            }

            @Test
            void toggleDishStatus_dishNotFound_throwsException() {
                when(dishPersistencePort.findById(1L)).thenReturn(Optional.empty());
                assertThrows(com.pragmafood.talentpool.square.domain.exceptions.DishNotFoundException.class,
                    () -> dishUseCase.toggleDishStatus(1L, true, 2L));
            }

            @Test
            void toggleDishStatus_restaurantNotFound_throwsException() {
                Dish dish = new Dish();
                Restaurant rest = new Restaurant();
                rest.setId(1L);
                dish.setRestaurant(rest);
                when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(dish));
                when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());
                assertThrows(com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException.class,
                    () -> dishUseCase.toggleDishStatus(1L, true, 2L));
            }

            @Test
            void listDishesByRestaurant_restaurantNotFound_throwsException() {
                when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());
                assertThrows(com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException.class,
                    () -> dishUseCase.listDishesByRestaurant(1L, "cat", 0, 1, "ASC"));
            }
        @Test
        void updateDish_success() {
            Dish dish = new Dish();
            Restaurant rest = new Restaurant();
            rest.setId(1L);
            rest.setOwnerId(2L);
            dish.setRestaurant(rest);
            when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(dish));
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(rest));
            when(dishPersistencePort.saveDish(any(Dish.class))).thenReturn(dish);
            Dish result = dishUseCase.updateDish(1L, 100, "desc", 2L);
            assertNotNull(result);
        }

        @Test
        void toggleDishStatus_success() {
            Dish dish = new Dish();
            Restaurant rest = new Restaurant();
            rest.setId(1L);
            rest.setOwnerId(2L);
            dish.setRestaurant(rest);
            when(dishPersistencePort.findById(1L)).thenReturn(Optional.of(dish));
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(rest));
            when(dishPersistencePort.saveDish(any(Dish.class))).thenReturn(dish);
            Dish result = dishUseCase.toggleDishStatus(1L, true, 2L);
            assertNotNull(result);
        }

        @Test
        void listDishesByRestaurant_success() {
            Restaurant rest = new Restaurant();
            rest.setId(1L);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(rest));
            PaginatedResult<Dish> paginated = new PaginatedResult<>(List.of(new Dish()), 0, 1, 1L, 1);
            when(dishPersistencePort.findDishesByRestaurantId(eq(1L), any(), eq(0), eq(1), any())).thenReturn(paginated);
            PaginatedResult<Dish> result = dishUseCase.listDishesByRestaurant(1L, "cat", 0, 1, "ASC");
            assertNotNull(result);
            assertEquals(1, result.getContent().size());
        }
    @Mock DishPersistencePort dishPersistencePort;
    @Mock RestaurantPersistencePort restaurantPersistencePort;
    @InjectMocks DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishUseCase = new DishUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Test
    void createDish_validDish_savesDish() {
        Dish dish = new Dish();
        dish.setName("Pizza");
        dish.setPrice(100);
        dish.setDescription("desc");
        dish.setImageUrl("img");
        dish.setCategory("cat");
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setOwnerId(2L);
        dish.setRestaurant(restaurant);
        dish.setOwnerId(2L);
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.saveDish(any(Dish.class))).thenReturn(dish);
        Dish saved = dishUseCase.createDish(dish);
        assertNotNull(saved);
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    void createDish_invalidName_throwsException() {
        Dish dish = new Dish();
        dish.setName("");
        assertThrows(InvalidFieldsException.class, () -> dishUseCase.createDish(dish));
    }

    // ...más pruebas unitarias para otros métodos y validaciones...
}
