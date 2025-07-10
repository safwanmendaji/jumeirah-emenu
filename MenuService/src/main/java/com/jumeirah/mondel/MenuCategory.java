package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "menu_categories")
@Data
public class MenuCategory {
    @Id
    private UUID menuCategoryId= UUID.randomUUID();
    private String sectionId;
    private String name;
    private String restaurantId;
    // Getters and setters
}