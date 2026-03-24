package com.pragmafood.talentpool.square.application.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record ToggleDishStatusRequest(
    @NotNull(message = "The active field is required")
    Boolean active
) {}
