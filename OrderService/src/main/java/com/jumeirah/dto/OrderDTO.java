package com.jumeirah.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String restaurantId;
    private String tableNo;
    private List<OrderItemDTO> orderItems;
}
