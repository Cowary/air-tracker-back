package org.cowary.arttrackerback.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Информация о состоянии сервиса")
public record HealthDto(
        @Schema(description = "Версия приложения", example = "2.4.0")
        String version,

        @Schema(description = "Статус работоспособности сервиса", example = "true")
        Boolean isHealthy
) {
}