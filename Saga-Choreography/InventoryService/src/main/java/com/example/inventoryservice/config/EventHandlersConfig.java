package com.example.inventoryservice.config;

import com.example.inventoryservice.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class EventHandlersConfig {

    @Autowired
    private OrderStatusUpdateEventHandler orderEventHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        log.info("EventHandlersConfig is invoked ::");
        return pe -> {
            log.info("1. EventHandlersConfig  consumer received ::{}", pe);
            orderEventHandler.updateOrder(pe.getPayment().getOrderId(), po -> {
              // po.setPaymentStatus(pe.getPaymentStatus());
                log.info("3. EventHandlersConfig  consumer received ::{}", po);
            }).subscribe();
        };
    }
}
