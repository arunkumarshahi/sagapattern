package com.example.inventoryservice.config;

import com.example.inventoryservice.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
@Slf4j
public class OrderConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderSink(){
        log.info("Sinks.Many invoked ");
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sink){
        log.info("orderSupplier invoked ");
        return sink::asFlux;
    }

}