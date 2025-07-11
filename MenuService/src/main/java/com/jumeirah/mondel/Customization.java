package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "customizations")
@Data
public class Customization {
    @Id
    private UUID customizationId = UUID.randomUUID();
    private String name;
    private String description;
    private int min;
    private int max;
    private String defaultValue;
    private String restaurantId;
    private List<CustomizationOption> list;
}

