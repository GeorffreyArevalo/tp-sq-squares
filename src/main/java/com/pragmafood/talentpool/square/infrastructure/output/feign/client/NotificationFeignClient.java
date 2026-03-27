package com.pragmafood.talentpool.square.infrastructure.output.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pragmafood.talentpool.square.infrastructure.output.feign.config.FeignConfiguration;
import com.pragmafood.talentpool.square.infrastructure.output.feign.requests.NotificationRequest;

@FeignClient(
    name = "notification-service", url = "${feign.client.notification-service.url}",
    configuration = FeignConfiguration.class
)
public interface NotificationFeignClient {

    @PostMapping("/notifications/order-ready")
    void sendSmsNotification(@RequestBody NotificationRequest notificationRequest);
}
