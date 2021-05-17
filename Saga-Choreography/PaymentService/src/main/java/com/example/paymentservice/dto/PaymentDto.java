package com.example.paymentservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
    private Integer orderId;
    private Integer userId;
    private Integer amount;
}
