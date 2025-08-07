package org.cowary.arttrackerback.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration implements WebMvcConfigurer {

    private final IncomingRequestLoggingInterceptor incomingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingInterceptor);
    }

    @Bean
    public RestTemplate restTemplate(OutgoingRequestLoggingInterceptor outgoingInterceptor) {
        // Используем BufferingClientHttpRequestFactory для возможности чтения тела несколько раз
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory());

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(outgoingInterceptor));

        return restTemplate;
    }
}
