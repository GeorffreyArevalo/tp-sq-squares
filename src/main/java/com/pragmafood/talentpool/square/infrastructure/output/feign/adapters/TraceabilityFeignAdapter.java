package com.pragmafood.talentpool.square.infrastructure.output.feign.adapters;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.clients.TraceabilityClientPort;
import com.pragmafood.talentpool.square.domain.enums.OrderStatus;
import com.pragmafood.talentpool.square.infrastructure.output.feign.client.TraceabilityFeignClient;
import com.pragmafood.talentpool.square.infrastructure.output.feign.requests.OrderTraceabilityRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TraceabilityFeignAdapter implements TraceabilityClientPort {

    private final TraceabilityFeignClient traceabilityFeignClient;

    @Override
    public void recordOrderStatusChange(Long orderId, Long clientId, OrderStatus previousStatus,
                                         OrderStatus newStatus, Long employeeId) {
        OrderTraceabilityRequest request = OrderTraceabilityRequest.builder()
                .orderId(orderId)
                .clientId(clientId)
                .previousStatus(previousStatus != null ? previousStatus.name() : null)
                .newStatus(newStatus.name())
                .employeeId(employeeId)
                .timestamp(LocalDateTime.now())
                .build();
        try {
            traceabilityFeignClient.recordOrderStatusChange(request);
        } catch (Exception e) {
            log.error("Error recording order status change for orderId {}: {}", orderId, e.getMessage());
        }
    }
}
