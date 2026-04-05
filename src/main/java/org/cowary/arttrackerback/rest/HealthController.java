package org.cowary.arttrackerback.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.cowary.arttrackerback.configuration.AppConfig;
import org.cowary.arttrackerback.rest.dto.response.HealthDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
@RequiredArgsConstructor
@Tag(name = "Health", description = "API для проверки работоспособности сервиса")
public class HealthController {
    private final AppConfig appConfig;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Проверка работоспособности",
            description = "Возвращает статус работоспособности сервиса и текущую версию приложения"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Сервис работает корректно",
                    content = @Content(schema = @Schema(implementation = HealthDto.class))
            )
    })
    public HealthDto getHealth() {
        return HealthDto.builder().isHealthy(Boolean.TRUE).version(appConfig.getVersion()).build();
    }
}
