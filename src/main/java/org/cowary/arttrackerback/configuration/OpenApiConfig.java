package org.cowary.arttrackerback.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("ArtTracker Back API")
                        .version("1.0.0")
                );
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                                .description("Provide the Bearer Token to access secured endpoints.")));
    }
}
