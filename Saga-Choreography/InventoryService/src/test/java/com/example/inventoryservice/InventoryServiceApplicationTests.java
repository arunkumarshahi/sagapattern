package com.example.inventoryservice;

import com.example.inventoryservice.dto.PaymentDto;
import com.example.inventoryservice.event.PaymentEvent;
import com.example.inventoryservice.event.PaymentStatus;
import com.example.inventoryservice.model.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

//@SpringBootTest
@Slf4j
class InventoryServiceApplicationTests {

    @Test

    void contextLoads() {
        Consumer<Integer> printValue=(val)->{
            log.info("Value of integer {}",val);};
        Flux<Integer> intValues=Flux.just(10,20,30,40,50);
       // intValues.subscribe(printValue);
       // printValue.accept(10);
        Consumer<PaymentEvent> paymentEvetHandler=   (pe) -> {
            log.info("1.EventHandlersConfig  consumer received ::{}", pe);
            updateOrder(pe.getPayment().getOrderId(), po -> {
                po.setPaymentStatus(pe.getPaymentStatus());
                log.info("2. EventHandlersConfig  consumer after processing  ::{}", po);
            });
        };
        paymentEvetHandler.accept(PaymentEvent.builder().payment(PaymentDto.builder().orderId(10).amount(10).build()).build());
//        Consumer<PaymentEvent> paymentEventConsumer= ()-> {
//            log.info("EventHandlersConfig is invoked ::");
//            return pe -> {
              //  log.info("EventHandlersConfig  consumer received ::{}", pe);
//                updateOrder(1, po -> {
//                    po.setPaymentStatus(PaymentStatus.CANCELLED);
//                    log.info("EventHandlersConfig  consumer received ::{}", po);
//
    }
private void updateOrder(int alue, Consumer<PurchaseOrder> po){
//    Mono.just(PurchaseOrder.builder().build()).subscribe(po);

    Mono.just(PurchaseOrder.builder().build()).doOnNext(po).doOnNext(this::updateOrder).subscribe();
}

    private void updateOrder(PurchaseOrder purchaseOrder) {
     log.info("3. inside last updateOrder :: ");
    }

}
