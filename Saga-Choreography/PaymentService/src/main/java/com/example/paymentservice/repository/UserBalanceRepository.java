package com.example.paymentservice.repository;

import com.example.paymentservice.model.UserBalance;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserBalanceRepository extends ReactiveCrudRepository<UserBalance, Integer> {

}
