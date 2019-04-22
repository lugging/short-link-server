package com.mimik.link.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class RedirectHandler {

    public Mono<ServerResponse> redirectUrl(String targetUrl) {
        return ServerResponse.temporaryRedirect(URI.create(targetUrl)).build();
    }
}