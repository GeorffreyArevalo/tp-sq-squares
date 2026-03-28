package com.pragmafood.talentpool.square.infrastructure.output.feign.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTraceabilityRequest {

    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private LocalDateTime timestamp;
}
