package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.OrderRequest;
import com.pragmafood.talentpool.square.application.dtos.responses.OrderResponse;
import com.pragmafood.talentpool.square.application.handlers.order.OrderHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
