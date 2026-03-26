package com.pragmafood.talentpool.square.infrastructure.output.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pragmafood.talentpool.square.infrastructure.output.feign.config.FeignConfiguration;
import com.pragmafood.talentpool.square.infrastructure.output.feign.responses.UserResponse;

@FeignClient(
    name = "user-service", url = "${feign.client.user-service.url}",
    configuration = FeignConfiguration.class
)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
