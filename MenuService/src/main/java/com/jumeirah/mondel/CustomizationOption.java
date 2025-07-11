package com.jumeirah.mondel;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomizationOption {
    private UUID customizationOptionId =UUID.randomUUID();
    private String name;
    private double price;
    private String altName;

}