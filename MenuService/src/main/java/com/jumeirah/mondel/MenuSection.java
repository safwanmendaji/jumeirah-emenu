package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "menu_sections")
@Data
public class MenuSection {
    @Id
    private UUID menuSectionId= UUID.randomUUID();
    private String menuId;
    private String sectionName;

}