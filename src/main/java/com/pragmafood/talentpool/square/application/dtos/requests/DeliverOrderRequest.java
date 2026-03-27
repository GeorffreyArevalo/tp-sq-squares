package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record DeliverOrderRequest(
    @NotBlank(message = "The security pin is required")
    String securityPin
) {}
