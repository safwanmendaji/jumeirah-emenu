package com.jumeirah.dto;


import lombok.Data;

import java.util.List;

@Data
public class CustomizationDTO {
    private String customizationID;
    private List<CustomizationOptionDTO> customizationOptions;

}
