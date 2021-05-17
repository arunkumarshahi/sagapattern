package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.PurchaseOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<PurchaseOrder, Integer> {

}