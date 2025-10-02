package org.cowary.arttrackerback.configuration;

import lombok.RequiredArgsConstructor;
import org.cowary.arttrackerback.integration.util.RestTemp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(OutgoingRequestLoggingInterceptor outgoingInterceptor) {
        return RestTemp.restTemplate(outgoingInterceptor);
    }
}
