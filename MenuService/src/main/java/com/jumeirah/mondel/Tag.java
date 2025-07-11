package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@Document(collection = "tags")
public class Tag {
    @Id
    private UUID tagId =UUID.randomUUID();
    private String title;
    private boolean display;
    private String code;
    private boolean filter;
    private Icon icon;
    private String type;
    private String restaurantId;
    // Getters and setters
}

