package com.program.dictionary.utils;

import com.program.dictionary.config.Config;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


/**
 * The Class RestUtils.
 */
@Component
public final class RestUtils implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private static WebClient webClient;

    private static HttpClient httpClient;


    private Config config = new Config();

private RestUtils() {
    httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getTimeoutConnectionUrl())
            .responseTimeout(Duration.ofMillis(config.getTimeoutConnectionUrl()))
            .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(config.getTimeoutConnectionUrl(), TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(config.getTimeoutConnectionUrl(), TimeUnit.MILLISECONDS)));
        webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
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
    private static <T>Mono<T> doRequest(final String url, final HttpMethod method, final T body,
                 final Consumer<HttpHeaders> headersConsumer, final ParameterizedTypeReference<T> responseClass) {
        return webClient.method(method).uri(url).headers(headersConsumer).retrieve().bodyToMono(responseClass);
    }
    public static <T> Mono<T> get(final String url, final Consumer<HttpHeaders> headers,
                                            final ParameterizedTypeReference<T> responseClass) {
        return doRequest(url, HttpMethod.GET, null, headers, responseClass);
    }

    /**
     * Post.
     *
     * @param <T>           the generic type
     * @param url           the url
     * @param headers       the headers
     * @param body          the body
     * @param responseClass the response class
     * @return the response entity
     */
    public static <T> Mono<T> post(final String url, final Consumer<HttpHeaders> headers, final T body,
                                             final ParameterizedTypeReference<T> responseClass) {
        return doRequest(url, HttpMethod.POST, body, headers, responseClass);
    }
    /**
     * Adds the request body.
     *
     * @param <T>     the generic type
     * @param body    the body
     * @param headers the headers
     * @return the http entity
     */
    @SuppressWarnings("unused")
	private static <T> HttpEntity<T> addRequestBody(final T body, final HttpHeaders headers) {
        if (Objects.nonNull(body)) {
            return new HttpEntity<>(body, headers);
        }
        return new HttpEntity<>(headers);
    }


    public static Consumer<HttpHeaders> httpHeaders(Map<String,String>headerMaps) {
        return new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.addAll( getHeader(headerMaps));
            }
        };
    }
    private static HttpHeaders getHeader(Map<String,String>headerMaps) {
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String,String> header : headerMaps.entrySet()) {
            headers.set(header.getKey(), header.getValue());
        }
        return headers;
    }
}