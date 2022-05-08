package com.program.dictionary.utils;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * The Class RestUtils.
 */
@Service
public final class RestUtils implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private static WebClient webClient;

    private RestUtils() {
        webClient = WebClient.builder().build();
    }

    /**
     * Call rest.
     *
     * @param <T>           the generic type
     * @param url           the url
     * @param method        the method
     * @param body          the body
     * @param headersConsumer       the headers
     * @param responseClass the response class
     * @return the response entity
     */
    private static <T>Mono<T> callRest(final String url, final HttpMethod method, final T body,
                 final Consumer<HttpHeaders> headersConsumer, final ParameterizedTypeReference<T> responseClass) {
        return webClient.method(method).uri(url).headers(headersConsumer).retrieve().bodyToMono(responseClass);
    }

    /**
     * Adds the request body.
     *
     * @param <T>     the generic type
     * @param body    the body
     * @param headers the headers
     * @return the http entity
     */
    private static <T> HttpEntity<T> addRequestBody(final T body, final HttpHeaders headers) {
        if (Objects.nonNull(body)) {
            return new HttpEntity<>(body, headers);
        }
        return new HttpEntity<>(headers);
    }


    public static HttpHeaders httpHeadersApplicationJSON() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CACHE_CONTROL, "no-cache");
        headers.set(HttpHeaders.CACHE_CONTROL, "no-cache");
        return headers;
    }

}