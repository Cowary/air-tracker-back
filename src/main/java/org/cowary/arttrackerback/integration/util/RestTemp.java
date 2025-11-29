package org.cowary.arttrackerback.integration.util;

import org.cowary.arttrackerback.configuration.OutgoingRequestLoggingInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Collections;

@Component
public class RestTemp {

    private final static RestTemplate restTemplate = restTemplate(new OutgoingRequestLoggingInterceptor());

    public static RestTemplate restTemplate(OutgoingRequestLoggingInterceptor outgoingInterceptor) {
        // Используем BufferingClientHttpRequestFactory для возможности чтения тела несколько раз
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory());

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(outgoingInterceptor));

        return restTemplate;
    }

    public <T> ResponseEntity<T> getRest(URL url, HttpEntity<String> request, Class<T> responseType) {
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                request,
                responseType);
    }

    public <T> T  getBody(URL url, Class<T> responseType) {
        return getBody(url, null, responseType);
    }

    public <T> T getBody(URL url, HttpEntity<String> request, Class<T> responseType) {
        T response = getRest(url, request, responseType).getBody();
        if (response == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        return response;
    }


}
