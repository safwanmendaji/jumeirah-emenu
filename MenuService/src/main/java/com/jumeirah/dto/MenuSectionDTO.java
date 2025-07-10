package com.jumeirah.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MenuSectionDTO {

    private UUID menuSectionId;
    private String sectionName;

//    private List<MenuCategoryDTO> menuCategories;
}