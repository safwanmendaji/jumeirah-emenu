package com.jumeirah.dto;

import com.jumeirah.mondel.WorkTiming;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class MenuResponseDTO {

    private UUID menuId;
    private String menuName;
    private List<MenuSectionDTO> menuSections;
    private List<WorkTiming> workTimings;
}













