package com.pragmafood.talentpool.square.domain.usecase;

import java.time.LocalDateTime;
import java.util.List;

import com.pragmafood.talentpool.square.domain.api.OrderServicePort;
import com.pragmafood.talentpool.square.domain.enums.ExceptionMessages;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.domain.exceptions.ActiveOrderExistsException;
import com.pragmafood.talentpool.square.domain.exceptions.DishNotFoundException;
import com.pragmafood.talentpool.square.domain.exceptions.InvalidFieldsException;
import com.pragmafood.talentpool.square.domain.exceptions.RestaurantNotFoundException;
import com.pragmafood.talentpool.square.domain.models.Dish;
import com.pragmafood.talentpool.square.domain.models.Order;
import com.pragmafood.talentpool.square.domain.models.OrderDish;
import com.pragmafood.talentpool.square.domain.spi.DishPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.OrderPersistencePort;
import com.pragmafood.talentpool.square.domain.spi.RestaurantPersistencePort;

public class OrderUseCase implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        DishPersistencePort dishPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
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
}
