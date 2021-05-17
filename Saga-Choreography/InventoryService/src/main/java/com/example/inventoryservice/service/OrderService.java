package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.PurchaseOrderDto;
import com.example.inventoryservice.event.OrderStatus;
import com.example.inventoryservice.model.PurchaseOrder;
import com.example.inventoryservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusPublisher publisher;
    @Transactional
    public Mono<PurchaseOrder> createOrder(Mono<PurchaseOrderDto> orderRequestDTO){
       // this.publisher.raiseOrderEvent(purchaseOrder, OrderStatus.ORDER_CREATED);
        return toPurchaseOrder(orderRequestDTO).flatMap(orderRepository::save).doOnNext(savedpurchaseOrder->
                this.publisher.raiseOrderEvent(savedpurchaseOrder, OrderStatus.ORDER_CREATED));

    }
    private Mono<PurchaseOrder> toPurchaseOrder(Mono<PurchaseOrderDto> purchaseOrderDto1){
       return  purchaseOrderDto1.map(purchaseOrderDto->{
           PurchaseOrder purchaseOrder=new PurchaseOrder();
           purchaseOrder.setProductId(purchaseOrderDto.getProductId());
           purchaseOrder.setPrice(purchaseOrderDto.getPrice());
           purchaseOrder.setUserId(purchaseOrderDto.getUserId());
            purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
           return purchaseOrder;
       });

    }
}
