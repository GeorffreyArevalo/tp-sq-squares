package com.pragmafood.talentpool.square.infrastructure.input.rest;

import com.pragmafood.talentpool.square.application.dtos.requests.DeliverOrderRequest;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Órdenes", description = "Gestión de órdenes")
public class OrderRestController {

    private final OrderHandler orderHandler;

    @Operation(
        summary = "Crear orden",
        description = "Crea una nueva orden.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = OrderRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Orden creada", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long clientId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderHandler.createOrder(orderRequest, clientId));
    }

    @Operation(
        summary = "Listar órdenes por estado",
        description = "Obtiene una lista paginada de órdenes filtradas por estado.",
        parameters = {
            @Parameter(name = "status", description = "Estado de la orden"),
            @Parameter(name = "page", description = "Página", example = "0"),
            @Parameter(name = "size", description = "Tamaño de página", example = "10")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes", content = @Content(schema = @Schema(implementation = PaginatedResponse.class)))
        }
    )
    @GetMapping
    public ResponseEntity<PaginatedResponse<OrderResponse>> listOrdersByStatus(
            @RequestParam("status") String status,
            @RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "size") int size,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.listOrdersByStatus(employeeId, status, page, size));
    }

    @Operation(
        summary = "Asignar orden",
        description = "Asigna una orden a un empleado.",
        parameters = {
            @Parameter(name = "orderId", description = "ID de la orden")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Orden asignada", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
        }
    )
    @PatchMapping("/{orderId}/assign")
    public ResponseEntity<OrderResponse> assignOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.assignOrder(orderId, employeeId));
    }

    @Operation(
        summary = "Marcar orden como lista",
        description = "Marca una orden como lista para entrega.",
        parameters = {
            @Parameter(name = "orderId", description = "ID de la orden")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Orden lista", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
        }
    )
    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<OrderResponse> markOrderAsReady(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.markOrderAsReady(orderId, employeeId));
    }

    @Operation(
        summary = "Entregar orden",
        description = "Entrega una orden.",
        parameters = {
            @Parameter(name = "orderId", description = "ID de la orden")
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = DeliverOrderRequest.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Orden entregada", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
        }
    )
    @PatchMapping("/{orderId}/deliver")
    public ResponseEntity<OrderResponse> deliverOrder(
            @PathVariable("orderId") Long orderId,
            @Valid @RequestBody DeliverOrderRequest deliverOrderRequest,
            @AuthenticationPrincipal Jwt jwt) {
        Long employeeId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.deliverOrder(orderId, employeeId, deliverOrderRequest));
    }

    @Operation(
        summary = "Cancelar orden",
        description = "Cancela una orden.",
        parameters = {
            @Parameter(name = "orderId", description = "ID de la orden")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Orden cancelada", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
        }
    )
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal Jwt jwt) {
        Long clientId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(orderHandler.cancelOrder(orderId, clientId));
    }
}
