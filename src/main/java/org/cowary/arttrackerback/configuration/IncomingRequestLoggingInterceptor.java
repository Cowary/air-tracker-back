package org.cowary.arttrackerback.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class IncomingRequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        logIncomingRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logIncomingResponse(response);

        if (response instanceof ContentCachingResponseWrapper) {
            try {
                ((ContentCachingResponseWrapper) response).copyBodyToResponse();
            } catch (Exception e) {
                LOGGER.error("Error copying response body", e);
            }
        }
    }

    private void logIncomingRequest(HttpServletRequest request) {
        try {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("\n=== INCOMING REQUEST ===\n");
            logMessage.append("Method: ").append(request.getMethod()).append("\n");
            logMessage.append("URI: ").append(request.getRequestURI());
            if (request.getQueryString() != null) {
                logMessage.append("?").append(request.getQueryString());
            }
            logMessage.append("\n");
            logMessage.append("Headers: ").append(getHeadersMap(request)).append("\n");

            if (request instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
                String body = new String(wrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                if (!body.isEmpty()) {
                    logMessage.append("Body: ").append(body).append("\n");
                }
            }

            LOGGER.debug(logMessage.toString());
        } catch (Exception e) {
            LOGGER.error("Error logging incoming request", e);
        }
    }

    private void logIncomingResponse(HttpServletResponse response) {
        try {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("\n=== INCOMING RESPONSE ===\n");
            logMessage.append("Status: ").append(response.getStatus()).append("\n");

            if (response instanceof ContentCachingResponseWrapper) {
                ContentCachingResponseWrapper wrapper = (ContentCachingResponseWrapper) response;
                String body = new String(wrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                if (!body.isEmpty()) {
                    logMessage.append("Body: ").append(body).append("\n");
                }
            }

            LOGGER.debug(logMessage.toString());
        } catch (Exception e) {
            LOGGER.error("Error logging incoming response", e);
        }
    }

    private Map<String, String> getHeadersMap(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headersMap.put(headerName, request.getHeader(headerName));
            }
        }
        return headersMap.isEmpty() ? Collections.emptyMap() : headersMap;
    }
}
