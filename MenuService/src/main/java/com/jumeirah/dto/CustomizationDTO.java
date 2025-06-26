package com.jumeirah.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CustomizationDTO {

    private UUID customizationId;
    private String name;
    private String description;
    private int min;
    private int max;
    private String defaultValue;
    private List<CustomizationOptionDTO> list;
}