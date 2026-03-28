package com.pragmafood.talentpool.square.infrastructure.output.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pragmafood.talentpool.square.infrastructure.output.feign.config.FeignConfiguration;
import com.pragmafood.talentpool.square.infrastructure.output.feign.requests.OrderTraceabilityRequest;

@FeignClient(
    name = "traceability-service", url = "${feign.client.traceability-service.url}",
    configuration = FeignConfiguration.class
)
public interface TraceabilityFeignClient {

    @PostMapping("/traceability")
    void recordOrderStatusChange(@RequestBody OrderTraceabilityRequest request);
}
