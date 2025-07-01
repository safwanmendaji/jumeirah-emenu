package com.jumeirah.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderItemDTO {
    private String itemId;
    private Double itemPrice;
    private Integer quantity;
    private List<CustomizationDTO> customizations;
}
