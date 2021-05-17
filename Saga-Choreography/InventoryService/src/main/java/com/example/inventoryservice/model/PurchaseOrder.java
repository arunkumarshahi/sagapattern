package com.example.inventoryservice.model;

import com.example.inventoryservice.event.InventoryStatus;
import com.example.inventoryservice.event.OrderStatus;
import com.example.inventoryservice.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@Table("PO_ORDER")
@NoArgsConstructor
@Builder
public class PurchaseOrder {
    @Id
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;

    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private InventoryStatus inventoryStatus;

//    @Version
//    private int version;

}
