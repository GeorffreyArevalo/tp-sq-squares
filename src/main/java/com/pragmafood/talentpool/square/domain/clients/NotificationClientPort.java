package com.pragmafood.talentpool.square.domain.clients;

public interface NotificationClientPort {

    void sendOrderReadyNotification(String clientName, String phone, String securityPin);
}
