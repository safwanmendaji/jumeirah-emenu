package com.jumeirah.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomizationOptionDTO {

    private UUID customazationOptionId;
    private String name;
    private double price;
    private String altName;
}