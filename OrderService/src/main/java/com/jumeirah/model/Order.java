package com.jumeirah.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Data
@Document(collection = "order")
public class Order {

    @Id
    private UUID orderId = UUID.randomUUID();
    private UUID restaurantId;
    private String tableNumber;
    private boolean isOrderClose;
    private LocalDateTime dateTime;

}


