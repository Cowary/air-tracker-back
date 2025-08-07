package org.cowary.arttrackerback.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OutgoingRequestLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logOutgoingRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logOutgoingResponse(response);

        return response;
    }

    private void logOutgoingRequest(HttpRequest request, byte[] body) {
        try {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("\n=== OUTGOING REQUEST ===\n");
            logMessage.append("Method: ").append(request.getMethod()).append("\n");
            logMessage.append("URL: ").append(request.getURI()).append("\n");
            logMessage.append("Headers: ").append(request.getHeaders()).append("\n");

            if (body.length > 0) {
                String bodyString = new String(body, StandardCharsets.UTF_8);
                logMessage.append("Body: ").append(bodyString).append("\n");
            }

            LOGGER.debug(logMessage.toString());
        } catch (Exception e) {
            LOGGER.debug("Error logging outgoing request", e);
        }
    }

    private void logOutgoingResponse(ClientHttpResponse response) {
        try {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("\n=== OUTGOING RESPONSE ===\n");
            logMessage.append("Status: ").append(response.getStatusCode()).append("\n");
            logMessage.append("Headers: ").append(response.getHeaders()).append("\n");

            String body = readResponseBody(response);
            if (body != null && !body.isEmpty()) {
                logMessage.append("Body: ").append(body).append("\n");
            }

            LOGGER.debug(logMessage.toString());
        } catch (Exception e) {
            LOGGER.debug("Error logging outgoing response", e);
        }
    }

    private String readResponseBody(ClientHttpResponse response) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            LOGGER.debug("Could not read response body", e);
            return "";
        }
    }
}