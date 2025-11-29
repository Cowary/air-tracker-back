package org.cowary.arttrackerback.configuration;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class IncomingRequestResponseLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequestResponse(wrappedRequest, wrappedResponse, duration);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequestResponse(ContentCachingRequestWrapper request,
                                    ContentCachingResponseWrapper response,
                                    long duration) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            uri += "?" + URLDecoder.decode(queryString, StandardCharsets.UTF_8);
        }
        int status = response.getStatus();

        String reqHeaders = getHeaders(request);
        String respHeaders = getHeaders(response);

        byte[] reqPayload = request.getContentAsByteArray();
        String reqBody = new String(reqPayload);
        byte[] respPayload = response.getContentAsByteArray();
        String respBody = new String(respPayload);

        var rq = new StringBuilder()
                .append("REQUEST:\n")
                .append("Method: ").append(method).append("\n")
                .append("URI: ").append(uri).append("\n")
                .append("Headers: ").append(reqHeaders).append("\n");
        if (StringUtils.isNotBlank(reqBody)) {
            rq.append("Body: ").append(reqBody).append("\n");
        }
        var rs = new StringBuilder()
                .append("RESPONSE:\n")
                .append("Status: ").append(status).append("\n")
                .append("Headers: ").append(respHeaders).append("\n");
        if (StringUtils.isNotBlank(respBody)) {
            rs.append("Body: ").append(respBody).append("\n");
        }
        log.info(rq.toString());
        log.info(rs.toString());
    }

    private String getHeaders(jakarta.servlet.http.HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                headers.put(name, request.getHeader(name));
            }
        }
        return headers.toString();
    }

    private String getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        for (String name : response.getHeaderNames()) {
            headers.put(name, response.getHeader(name));
        }
        return headers.toString();
    }
}
