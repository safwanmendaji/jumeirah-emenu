package com.jumeirah.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "orderItems")
public class OrderItems {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID itemId;
    private UUID orderId;
    private String customizationId;
    private List<OrderItemCustomization> customizationsOption;
    private ItemStatus itemStatus;
    private Double itemPrice;
}



