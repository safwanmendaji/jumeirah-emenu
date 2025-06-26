package com.jumeirah.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IconDTO {
    private UUID iconId;
    private String size;
    private String src;
    private String alt;
    private String name;
}