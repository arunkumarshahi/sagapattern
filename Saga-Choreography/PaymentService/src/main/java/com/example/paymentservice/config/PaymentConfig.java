package com.example.paymentservice.config;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.event.OrderEvent;
import com.example.paymentservice.event.OrderStatus;
import com.example.paymentservice.event.PaymentEvent;
import com.example.paymentservice.event.PaymentStatus;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PaymentConfig {
    private final PaymentService service;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        log.info("** payment process has started ***");
        return flux -> flux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent event){
        log.info("** OrderEvent received ** {}",event);
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            //return Mono.fromSupplier(() -> this.service.newOrderEvent(event).block());
            return this.service.newOrderEvent(event);
           // return Mono.fromSupplier(() -> new PaymentEvent(PaymentDto.builder().amount(100).userId(1).build(), PaymentStatus.RESERVED));
        }else{
            //return Mono.fromRunnable(() -> this.service.cancelOrderEvent(event));
            return this.service.cancelOrderEvent(event);
            //return Mono.fromSupplier(() -> new PaymentEvent(PaymentDto.builder().amount(100).userId(1).build(), PaymentStatus.REJECTED));
        }
    }

}
