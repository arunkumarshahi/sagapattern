package com.example.paymentservice.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("user_balance")
public class UserBalance {
    @Id
    private Integer userId;
    private int balance;
}
