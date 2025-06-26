package com.jumeirah.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MenuCategoryDTO {

    private UUID menuCategoryId;
    private String name;
    private List<MenuItemDTO> items;
}