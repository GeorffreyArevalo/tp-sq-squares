package com.pragmafood.talentpool.square.infrastructure.output.feign.adapters;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.clients.NotificationClientPort;
import com.pragmafood.talentpool.square.infrastructure.output.feign.client.NotificationFeignClient;
import com.pragmafood.talentpool.square.infrastructure.output.feign.requests.NotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationFeignAdapter implements NotificationClientPort {

    private final NotificationFeignClient notificationFeignClient;

    @Override
    public void sendOrderReadyNotification(String clientName, String phone, String securityPin) {
        NotificationRequest request = NotificationRequest.builder()
                .customerName(clientName)
                .customerPhoneNumber(phone)
                .securityPin(securityPin)
                .build();
        try {
            notificationFeignClient.sendSmsNotification(request);
        } catch (Exception e) {
            log.error("Error sending order ready notification to {}: {}", phone, e.getMessage());
        }
    }
}
