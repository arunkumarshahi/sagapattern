package com.example.paymentservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class UserTransaction {
    @Id
    private Integer orderId;
    private int userId;
    private int amount;
}
