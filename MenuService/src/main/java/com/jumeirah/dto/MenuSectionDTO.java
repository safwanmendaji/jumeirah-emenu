package com.jumeirah.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuSectionDTO {
    private String sectionName;
    private List<MenuCategoryDTO> menuCategories;
}