package com.vinsguru.saga.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    @Qualifier("payment")
    public WebClient paymentClient(@Value("${service.endpoints.payment}") String endpoint){
        log.info("paymentClient is invoked from orchestrator :: {}",endpoint);
        return WebClient.builder()
                .baseUrl(endpoint)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .build();
    }

    @Bean
    @Qualifier("inventory")
    public WebClient inventoryClient(@Value("${service.endpoints.inventory}") String endpoint){
        log.info("inventoryClient is invoked from orchestrator :: {}",endpoint);
        return WebClient.builder()
                .baseUrl(endpoint)
                .filter(WebClientFilter.logRequest())
                .build();
    }
    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (log.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                //append clientRequest method and url
                log.info("request body ",clientRequest.body());

            }
            return Mono.just(clientRequest);
        });
    }

    ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (log.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                //append clientRequest method and url

                log.debug("Response of :: {}",clientResponse);
            }
            return Mono.just(clientResponse);
        });
    }
}
