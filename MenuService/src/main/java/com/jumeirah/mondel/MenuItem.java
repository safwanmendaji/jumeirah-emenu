package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

    @Document(collection = "menu_items")
    @Data
    public class MenuItem {
        @Id
        private UUID menuItem= UUID.randomUUID();
        private String categoryId;
        private String name;
        private String description;
        private double price;
        private String image;
        private String commentCode;
        private List<String> customizationIds;
        private List<String> tagIds;

        // Getters and setters
    }