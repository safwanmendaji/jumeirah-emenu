package com.jumeirah.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
public class OrderItemCustomization {

    private UUID customizationId;
    private Double price;

}
