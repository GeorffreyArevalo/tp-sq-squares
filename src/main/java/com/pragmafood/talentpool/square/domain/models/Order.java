package com.pragmafood.talentpool.square.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import com.pragmafood.talentpool.square.domain.enums.OrderStatus;

public class Order {

    private Long id;
    private Long clientId;
    private Long restaurantId;
    private List<OrderDish> dishes;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private Long assignedEmployeeId;
    private String securityPin;

    public Order() {
    }

    public Order(Long id, Long clientId, Long restaurantId, List<OrderDish> dishes, OrderStatus status, LocalDateTime createdAt, Long assignedEmployeeId, String securityPin) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.status = status;
        this.createdAt = createdAt;
        this.assignedEmployeeId = assignedEmployeeId;
        this.securityPin = securityPin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDish> dishes) {
        this.dishes = dishes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Long assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }
}
