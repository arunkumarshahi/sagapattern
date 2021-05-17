package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.event.OrderEvent;
import com.example.paymentservice.event.PaymentEvent;
import com.example.paymentservice.event.PaymentStatus;
import com.example.paymentservice.event.PurchaseOrderDto;
import com.example.paymentservice.model.UserBalance;
import com.example.paymentservice.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
private final UserBalanceRepository userBalanceRepository;
    public Mono<PaymentEvent> newOrderEvent(OrderEvent orderEvent){
        PurchaseOrderDto purchaseOrderDto= orderEvent.getPurchaseOrder();
        log.info("** newOrderEvent created ** {}",purchaseOrderDto);
        purchaseOrderDto.getPrice();
        return userBalanceRepository.findById(purchaseOrderDto.getUserId())
                //.map(userBalance -> userBalance.getBalance())
                .filter(userBalance->userBalance.getBalance()>=purchaseOrderDto.getPrice())
                .map(ub->{
                    ub.setBalance(ub.getBalance()-purchaseOrderDto.getPrice());
                    userBalanceRepository.save(ub).subscribe(System.out::println);
                    return new PaymentEvent(PaymentDto.builder().userId(ub.getUserId()).
                            orderId(purchaseOrderDto.getOrderId()).
                            amount(purchaseOrderDto.getPrice()).build()
                            , PaymentStatus.RESERVED);
                }) .switchIfEmpty(Mono.defer(() -> Mono.just(new PaymentEvent(null
                        , PaymentStatus.REJECTED))));
    }

    public Mono<PaymentEvent> cancelOrderEvent(OrderEvent orderEvent){
        log.info("** newOrderEvent cancelOrderEvent ** {}",orderEvent);
        PurchaseOrderDto purchaseOrderDto= orderEvent.getPurchaseOrder();
        purchaseOrderDto.getPrice();
        return userBalanceRepository.findById(purchaseOrderDto.getUserId()).map(userBalance -> {
              userBalanceRepository.save(userBalance);
             return new PaymentEvent(null,PaymentStatus.CANCELLED);
        });

    }
}
