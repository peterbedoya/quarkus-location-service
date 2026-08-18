package com.telemetry.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record LocationRequest(
    @NotNull(message = "deviceId es obligatorio")
    String deviceId,

    @NotNull(message = "latitude es obligatoria")
    Double latitude,

    @NotNull(message = "longitude es obligatoria")
    Double longitude,

    LocalDateTime recordedAt
) {}
