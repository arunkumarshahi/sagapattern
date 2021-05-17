package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.PurchaseOrderDto;
import com.example.inventoryservice.event.OrderEvent;
import com.example.inventoryservice.event.OrderStatus;
import com.example.inventoryservice.model.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderStatusPublisher {

    private final Sinks.Many<OrderEvent> orderSink;

    public void raiseOrderEvent(final PurchaseOrder purchaseOrder, OrderStatus orderStatus){
        log.info("Raising order create event :: {}",purchaseOrder);
        var dto = PurchaseOrderDto.of(
                purchaseOrder.getId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getPrice(),
                purchaseOrder.getUserId()
        );
        var orderEvent = new OrderEvent(dto, orderStatus);
        log.info("Emit order event :: {}",orderEvent);
        this.orderSink.tryEmitNext(orderEvent);
    }

}
