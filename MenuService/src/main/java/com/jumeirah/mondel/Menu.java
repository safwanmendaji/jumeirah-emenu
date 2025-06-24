package com.jumeirah.mondel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "menus")
@Data
public class Menu {
    @Id
    private UUID menuId= UUID.randomUUID();
    private String restaurantId;
    private String menuName;

}