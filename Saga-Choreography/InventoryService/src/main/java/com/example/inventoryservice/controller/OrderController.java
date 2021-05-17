package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.PurchaseOrderDto;
import com.example.inventoryservice.model.PurchaseOrder;
import com.example.inventoryservice.service.OrderService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class OrderController {

    @Bean
    RouterFunction<ServerResponse> createOrderRoute(OrderService orderService) {
        return route().POST("/create",
                req ->  {
                    Mono<PurchaseOrderDto> orderDtoMono = req.body(toMono(PurchaseOrderDto.class));
                    return ServerResponse.ok().body(orderService.createOrder(orderDtoMono),PurchaseOrder.class);

                }).build();
    }
}
