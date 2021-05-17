package com.example.inventoryservice.config;

import com.example.inventoryservice.event.OrderStatus;
import com.example.inventoryservice.event.PaymentStatus;
import com.example.inventoryservice.model.PurchaseOrder;
import com.example.inventoryservice.repository.OrderRepository;
import com.example.inventoryservice.service.OrderStatusPublisher;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

@Service
@Slf4j
public class OrderStatusUpdateEventHandler {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderStatusPublisher publisher;

    @Transactional

    public Mono<Void> updateOrder(final Integer id, Consumer<PurchaseOrder> consumer) {
        log.info("before Update order in OrderStatusUpdateEventHandler");

        this.repository
                .findById(id).doOnNext(consumer).doOnNext(this::updateOrder).subscribe(System.out::println);
        log.info("2. Update order is invoked in OrderStatusUpdateEventHandler");
        return Mono.empty();
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        log.info("Update order is invoked with  purchaseOrder ={}",purchaseOrder);
        if (Objects.isNull(purchaseOrder) || Objects.isNull(purchaseOrder.getPaymentStatus()))
            return;
        var isComplete = PaymentStatus.RESERVED.equals(purchaseOrder.getPaymentStatus())
                || PaymentStatus.CANCELLED.equals(purchaseOrder.getPaymentStatus());
                //&& InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete) {
            this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
        }
    }
}

