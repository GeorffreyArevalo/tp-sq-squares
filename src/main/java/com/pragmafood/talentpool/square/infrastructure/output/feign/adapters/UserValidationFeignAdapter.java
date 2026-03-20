package com.pragmafood.talentpool.square.infrastructure.output.feign.adapters;

import org.springframework.stereotype.Component;

import com.pragmafood.talentpool.square.domain.clients.UserClientPort;
import com.pragmafood.talentpool.square.infrastructure.output.feign.client.UserFeignClient;
import com.pragmafood.talentpool.square.infrastructure.output.feign.responses.UserResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidationFeignAdapter implements UserClientPort {

    private static final String ROLE_OWNER = "OWNER";

    private final UserFeignClient userFeignClient;

    @Override
    public boolean hasOwnerRole(Long userId) {
        try {
            UserResponse user = userFeignClient.getUserById(userId);
            return user != null && ROLE_OWNER.equalsIgnoreCase(user.getRole());
        } catch (Exception e) {
            return false;
        }
    }
}
