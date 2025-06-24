package com.jumeirah.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuCategoryDTO {
    private String name;
    private List<MenuItemDTO> items;
}