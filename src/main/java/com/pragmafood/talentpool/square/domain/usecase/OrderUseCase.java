package com.pragmafood.talentpool.square.domain.usecase;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.clients.NotificationClientPort;
import com.pragmafood.talentpool.square.domain.clients.UserClientPort;
import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.exceptions.ActiveOrderExistsException;
import com.pragmafood.talentpool.square.domain.exceptions.DishNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.EmployeeRestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidSecurityPinException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderAlreadyInPreparationException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotAssignedToEmployeeException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotBelongsToClientException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotBelongsToRestaurantException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotInPreparationException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotPendingException;
import com.pragmafood.talentpool.square.domain.exceptions.OrderNotReadyException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.EmployeeRestaurant;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.OrderDish;
import com.pragmafood.talentpool.square.domain.models.PaginatedResult;
import com.pragmafood.talentpool.square.domain.spi.DishPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.EmployeeRestaurantPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.OrderPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;

public class OrderUseCase implements OrderServicePort {

    private static final int PIN_BOUND = 10000;
    private static final String PIN_FORMAT = "%04d";

    private final OrderPersistencePort orderPersistencePort;
    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    private final UserClientPort userClientPort;
    private final NotificationClientPort notificationClientPort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        DishPersistencePort dishPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort,
                        EmployeeRestaurantPersistencePort employeeRestaurantPersistencePort,
                        UserClientPort userClientPort,
                        NotificationClientPort notificationClientPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.employeeRestaurantPersistencePort = employeeRestaurantPersistencePort;
        this.userClientPort = userClientPort;
        this.notificationClientPort = notificationClientPort;
    }

    @Override
    public Order createOrder(Order order) {
        validateRestaurantId(order.getRestaurantId());
        validateDishes(order.getDishes());

        restaurantPersistencePort.findById(order.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND.getMessage()));

        boolean hasActiveOrder = orderPersistencePort.existsByClientIdAndStatusIn(
                order.getClientId(),
                List.of(OrderStatus.PENDING, OrderStatus.IN_PREPARATION, OrderStatus.READY)
        );
        if (hasActiveOrder) {
            throw new ActiveOrderExistsException(ExceptionMessages.ACTIVE_ORDER_EXISTS.getMessage());
        }

        List<Long> dishIds = order.getDishes().stream()
                .map(OrderDish::getDishId)
                .toList();

        List<Dish> dishes = dishPersistencePort.findAllByIds(dishIds);

        if (dishes.size() != dishIds.size()) {
            throw new DishNotFoundException(ExceptionMessages.ORDER_DISH_NOT_FOUND.getMessage());
        }

        boolean allSameRestaurant = dishes.stream()
                .allMatch(dish -> dish.getRestaurant().getId().equals(order.getRestaurantId()));
        if (!allSameRestaurant) {
            throw new InvalidFieldsException(ExceptionMessages.ORDER_DISHES_NOT_SAME_RESTAURANT.getMessage());
        }

        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        return orderPersistencePort.saveOrder(order);
    }

    private void validateRestaurantId(Long restaurantId) {
        if (restaurantId == null) {
            throw new InvalidFieldsException(ExceptionMessages.ORDER_RESTAURANT_ID_REQUIRED.getMessage());
        }
    }

    @Override
    public PaginatedResult<Order> listOrdersByStatus(Long employeeId, OrderStatus status, int page, int size) {
        EmployeeRestaurant employeeRestaurant = employeeRestaurantPersistencePort.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EmployeeRestaurantNotFoundException(ExceptionMessages.EMPLOYEE_RESTAURANT_NOT_FOUND.getMessage()));

        return orderPersistencePort.findOrdersByRestaurantIdAndStatus(employeeRestaurant.getRestaurantId(), status, page, size);
    }

    @Override
    public Order assignOrderToEmployee(Long orderId, Long employeeId) {
        EmployeeRestaurant employeeRestaurant = employeeRestaurantPersistencePort.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EmployeeRestaurantNotFoundException(ExceptionMessages.EMPLOYEE_RESTAURANT_NOT_FOUND.getMessage()));

        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessages.ORDER_NOT_FOUND.getMessage()));

        if (!order.getRestaurantId().equals(employeeRestaurant.getRestaurantId())) {
            throw new OrderNotBelongsToRestaurantException(ExceptionMessages.ORDER_NOT_BELONGS_TO_RESTAURANT.getMessage());
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderNotPendingException(ExceptionMessages.ORDER_NOT_PENDING.getMessage());
        }

        order.setAssignedEmployeeId(employeeId);
        order.setStatus(OrderStatus.IN_PREPARATION);

        return orderPersistencePort.saveOrder(order);
    }

    private void validateDishes(List<OrderDish> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            throw new InvalidFieldsException(ExceptionMessages.ORDER_DISHES_REQUIRED.getMessage());
        }
        for (OrderDish dish : dishes) {
            if (dish.getQuantity() == null || dish.getQuantity() <= 0) {
                throw new InvalidFieldsException(ExceptionMessages.ORDER_DISH_QUANTITY_INVALID.getMessage());
            }
        }
    }

    @Override
    public Order markOrderAsReady(Long orderId, Long employeeId) {
        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessages.ORDER_NOT_FOUND.getMessage()));

        if (!employeeId.equals(order.getAssignedEmployeeId())) {
            throw new OrderNotAssignedToEmployeeException(ExceptionMessages.ORDER_NOT_ASSIGNED_TO_EMPLOYEE.getMessage());
        }

        if (order.getStatus() != OrderStatus.IN_PREPARATION) {
            throw new OrderNotInPreparationException(ExceptionMessages.ORDER_NOT_IN_PREPARATION.getMessage());
        }

        String securityPin = generateSecurityPin();
        order.setSecurityPin(securityPin);
        order.setStatus(OrderStatus.READY);

        Order savedOrder = orderPersistencePort.saveOrder(order);

        String clientName = userClientPort.getUserFullName(order.getClientId());
        String clientPhone = userClientPort.getUserPhone(order.getClientId());
        notificationClientPort.sendOrderReadyNotification(clientName, clientPhone, securityPin);

        return savedOrder;
    }

    private String generateSecurityPin() {
        SecureRandom random = new SecureRandom();
        int pin = random.nextInt(PIN_BOUND);
        return String.format(PIN_FORMAT, pin);
    }

    @Override
    public Order deliverOrder(Long orderId, Long employeeId, String securityPin) {
        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessages.ORDER_NOT_FOUND.getMessage()));

        if (!employeeId.equals(order.getAssignedEmployeeId())) {
            throw new OrderNotAssignedToEmployeeException(ExceptionMessages.ORDER_NOT_ASSIGNED_TO_EMPLOYEE.getMessage());
        }

        if (order.getStatus() != OrderStatus.READY) {
            throw new OrderNotReadyException(ExceptionMessages.ORDER_NOT_READY.getMessage());
        }

        if (!securityPin.equals(order.getSecurityPin())) {
            throw new InvalidSecurityPinException(ExceptionMessages.INVALID_SECURITY_PIN.getMessage());
        }

        order.setStatus(OrderStatus.DELIVERED);

        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public Order cancelOrder(Long orderId, Long clientId) {
        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(ExceptionMessages.ORDER_NOT_FOUND.getMessage()));

        if (!clientId.equals(order.getClientId())) {
            throw new OrderNotBelongsToClientException(ExceptionMessages.ORDER_NOT_BELONGS_TO_CLIENT.getMessage());
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderAlreadyInPreparationException(ExceptionMessages.ORDER_ALREADY_IN_PREPARATION.getMessage());
        }

        order.setStatus(OrderStatus.CANCELLED);

        return orderPersistencePort.saveOrder(order);
    }
}
