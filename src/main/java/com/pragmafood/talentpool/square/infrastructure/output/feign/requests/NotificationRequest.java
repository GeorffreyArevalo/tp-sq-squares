package com.pragmafood.talentpool.square.infrastructure.output.feign.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private String customerName;
    private String customerPhoneNumber;
    private String securityPin;
}
