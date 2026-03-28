package com.pragmafood.talentpool.square.domain.usecase;

import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.clients.NotificationClientPort;
import com.pragmafood.talentpool.square.domain.clients.TraceabilityClientPort;
import com.pragmafood.talentpool.square.domain.clients.UserClientPort;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.exceptions.*;
import com.pragmafood.talentpool.square.domain.models.*;
import com.pragmafood.talentpool.square.domain.spi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {
        @Test
        void listOrdersByStatus_success() {
            EmployeeRestaurant empRest = new EmployeeRestaurant();
            empRest.setRestaurantId(10L);
            PaginatedResult<Order> paginated = new PaginatedResult<>(List.of(new Order()), 0, 1, 1L, 1);
            when(employeeRestaurantPersistencePort.findByEmployeeId(1L)).thenReturn(Optional.of(empRest));
            when(orderPersistencePort.findOrdersByRestaurantIdAndStatus(eq(10L), any(), eq(0), eq(1))).thenReturn(paginated);
            PaginatedResult<Order> result = orderUseCase.listOrdersByStatus(1L, OrderStatus.PENDING, 0, 1);
            assertNotNull(result);
            assertEquals(1, result.getContent().size());
        }

        @Test
        void assignOrderToEmployee_success() {
            EmployeeRestaurant empRest = new EmployeeRestaurant();
            empRest.setRestaurantId(10L);
            Order order = new Order();
            order.setRestaurantId(10L);
            order.setStatus(OrderStatus.PENDING);
            Order savedOrder = new Order();
            savedOrder.setRestaurantId(10L);
            savedOrder.setStatus(OrderStatus.IN_PREPARATION);
            when(employeeRestaurantPersistencePort.findByEmployeeId(2L)).thenReturn(Optional.of(empRest));
            when(orderPersistencePort.findById(1L)).thenReturn(Optional.of(order));
            when(orderPersistencePort.saveOrder(any(Order.class))).thenReturn(savedOrder);
            when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Restaurant(10L, "", "R", "NIT", "P", "2L", 1L)));
            Order result = orderUseCase.assignOrderToEmployee(1L, 2L);
            assertNotNull(result);
            assertEquals(OrderStatus.IN_PREPARATION, result.getStatus());
        }

        @Test
        void markOrderAsReady_success() {
            Order order = new Order();
            order.setAssignedEmployeeId(2L);
            order.setStatus(OrderStatus.IN_PREPARATION);
            order.setRestaurantId(10L);
            order.setClientId(5L);
            Order savedOrder = new Order();
            savedOrder.setRestaurantId(10L);
            savedOrder.setStatus(OrderStatus.READY);
            savedOrder.setAssignedEmployeeId(2L);
            savedOrder.setClientId(5L);
            when(orderPersistencePort.findById(1L)).thenReturn(Optional.of(order));
            when(orderPersistencePort.saveOrder(any(Order.class))).thenReturn(savedOrder);
            when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Restaurant(10L, "", "R", "NIT", "P", "2L", 1L)));
            when(userClientPort.getUserFullName(anyLong())).thenReturn("Cliente");
            when(userClientPort.getUserPhone(anyLong())).thenReturn("123456789");
            Order result = orderUseCase.markOrderAsReady(1L, 2L);
            assertNotNull(result);
            assertEquals(OrderStatus.READY, result.getStatus());
        }

        @Test
        void deliverOrder_success() {
            Order order = new Order();
            order.setAssignedEmployeeId(2L);
            order.setStatus(OrderStatus.READY);
            order.setSecurityPin("1234");
            order.setRestaurantId(10L);
            order.setClientId(5L);
            Order savedOrder = new Order();
            savedOrder.setStatus(OrderStatus.DELIVERED);
            savedOrder.setRestaurantId(10L);
            savedOrder.setClientId(5L);
            when(orderPersistencePort.findById(1L)).thenReturn(Optional.of(order));
            when(orderPersistencePort.saveOrder(any(Order.class))).thenReturn(savedOrder);
            when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Restaurant(10L, "", "R", "NIT", "P", "2L", 1L)));
            Order result = orderUseCase.deliverOrder(1L, 2L, "1234");
            assertNotNull(result);
            assertEquals(OrderStatus.DELIVERED, result.getStatus());
        }

        @Test
        void cancelOrder_success() {
            Order order = new Order();
            order.setClientId(5L);
            order.setStatus(OrderStatus.PENDING);
            order.setRestaurantId(10L);
            Order savedOrder = new Order();
            savedOrder.setStatus(OrderStatus.CANCELLED);
            savedOrder.setRestaurantId(10L);
            savedOrder.setClientId(5L);
            when(orderPersistencePort.findById(1L)).thenReturn(Optional.of(order));
            when(orderPersistencePort.saveOrder(any(Order.class))).thenReturn(savedOrder);
            when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Restaurant(10L, "", "R", "NIT", "P", "2L", 1L)));
            Order result = orderUseCase.cancelOrder(1L, 5L);
            assertNotNull(result);
            assertEquals(OrderStatus.CANCELLED, result.getStatus());
        }
    @Mock private OrderPersistencePort orderPersistencePort;
    @Mock private DishPersistencePort dishPersistencePort;
    @Mock private RestaurantPersistencePort restaurantPersistencePort;
    @Mock private EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    @Mock private UserClientPort userClientPort;
    @Mock private NotificationClientPort notificationClientPort;
    @Mock private TraceabilityClientPort traceabilityClientPort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @Test
    void createOrder_validOrder_savesOrder() {
        Order order = new Order();
        order.setRestaurantId(1L);
        order.setClientId(2L);
        order.setDishes(List.of(new OrderDish(1L,1L, 2)));
        // Crear un Dish con el campo restaurant inicializado
        Dish dish = new Dish();
        Restaurant dishRestaurant = new Restaurant();
        dishRestaurant.setId(1L);
        dish.setRestaurant(dishRestaurant);
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(new Restaurant(1L, "", "R", "NIT", "P", "2L", 1L)));
        when(orderPersistencePort.existsByClientIdAndStatusIn(anyLong(), anyList())).thenReturn(false);
        when(dishPersistencePort.findAllByIds(anyList())).thenReturn(List.of(dish));
        when(orderPersistencePort.saveOrder(any(Order.class))).thenReturn(order);
        Order saved = orderUseCase.createOrder(order);
        assertNotNull(saved);
        verify(orderPersistencePort).saveOrder(order);
    }

    @Test
    void createOrder_withActiveOrder_throwsException() {
        Order order = new Order();
        order.setRestaurantId(1L);
        order.setClientId(2L);
        order.setDishes(List.of(new OrderDish(1L,1L, 2)));
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(new Restaurant()));
        when(orderPersistencePort.existsByClientIdAndStatusIn(anyLong(), anyList())).thenReturn(true);
        assertThrows(ActiveOrderExistsException.class, () -> orderUseCase.createOrder(order));
    }

    // ...más pruebas unitarias para otros métodos y validaciones...
}
