package com.jumeirah.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MenuItemDTO {

    private UUID menuItemId;
    private String name;
    private String description;
    private double price;
    private String image;
    private String commentCode;
    private List<CustomizationDTO> customizations;
    private List<TagDTO> tags;
}