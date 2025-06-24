package com.jumeirah.dto;

import com.jumeirah.mondel.WorkTiming;
import lombok.Data;
import java.util.List;

@Data
public class MenuResponseDTO {
    private String menuName;
    private List<MenuSectionDTO> menuSections;
    private List<WorkTiming> workTimings;
}













