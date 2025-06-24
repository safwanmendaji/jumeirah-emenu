package com.jumeirah.service;


import com.jumeirah.dto.MenuResponseDTO;
import com.jumeirah.mondel.*;

import java.util.List;

public interface MenuService {
    MenuResponseDTO getMenuWithDetails(String menuId);

    List<MenuResponseDTO> getMenuWithDetailsByRestId(String restId);

    Menu createMenu(Menu menu);

    MenuSection createMenuSection(MenuSection section);

    MenuCategory createMenuCategory(MenuCategory category);

    MenuItem createMenuItem(MenuItem item);

    Customization createCustomization(Customization customization);

    Tag createTag(Tag tag);

    WorkTiming createWorkTiming(WorkTiming timing);
}