package com.jumeirah.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomizationDTO {
    private String name;
    private String description;
    private int min;
    private int max;
    private String defaultValue;
    private List<CustomizationOptionDTO> list;
}