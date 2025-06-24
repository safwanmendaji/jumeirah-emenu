package com.jumeirah.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private String commentCode;
    private List<CustomizationDTO> customizations;
    private List<TagDTO> tags;
}