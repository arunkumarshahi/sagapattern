package com.example.paymentservice.repository;

import com.example.paymentservice.model.UserBalance;
import com.example.paymentservice.model.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {
}
