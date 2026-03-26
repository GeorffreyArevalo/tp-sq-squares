package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.dtos.responses.PaginatedResponse;
import com.pragmafood.talentpool.square.application.handlers.order.OrderHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderHandler orderHandler;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long clientId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderHandler.createOrder(orderRequest, clientId));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<OrderResponse>> listOrdersByStatus(
            @RequestParam("status") String status,
            @RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "size") int size,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.listOrdersByStatus(employeeId, status, page, size));
    }

    @PatchMapping("/{orderId}/assign")
    public ResponseEntity<OrderResponse> assignOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.assignOrder(orderId, employeeId));
    }
}
