package com.jumeirah.service;

import com.jumeirah.dto.MenuResponseDTO;
import com.jumeirah.mondel.*;

import java.util.List;

public interface MenuService {
    // Get operations
    MenuResponseDTO getMenuWithDetails(String menuId);
    List<MenuResponseDTO> getMenuWithDetailsByRestId(String restId);

    // Create operations
    Menu createMenu(Menu menu);
    MenuSection createMenuSection(MenuSection section);
    MenuCategory createMenuCategory(MenuCategory category);
    MenuItem createMenuItem(MenuItem item);
    Customization createCustomization(Customization customization);
    Tag createTag(Tag tag);
    WorkTiming createWorkTiming(WorkTiming timing);

    // Update operations
    Menu updateMenu(String menuId, Menu updatedMenu);
    MenuSection updateMenuSection(String sectionId, MenuSection updatedSection);
    MenuCategory updateMenuCategory(String categoryId, MenuCategory updatedCategory);
    MenuItem updateMenuItem(String itemId, MenuItem updatedItem);
    Customization updateCustomization(String customizationId, Customization updatedCustomization);
    Tag updateTag(String tagId, Tag updatedTag);
    WorkTiming updateWorkTiming(String timingId, WorkTiming updatedTiming);

    // **Delete Menu**
    void deleteMenu(String menuId);

    // **Delete Menu Section**
    void deleteMenuSection(String sectionId);

    // **Delete Menu Category**
    void deleteMenuCategory(String categoryId);

    // **Delete Menu Item**
    void deleteMenuItem(String itemId);

    // **Delete Customization**
    void deleteCustomization(String customizationId);

    // **Delete Tag**
    void deleteTag(String tagId);

    // **Delete Work Timing**
    void deleteWorkTiming(String timingId);

    List<MenuCategory> getAllCategories();
    MenuCategory getCategoryById(String categoryId);

    List<MenuSection> getAllSections();
    MenuSection getSectionById(String id);

    List<MenuItem> getAllItems();
    MenuItem getItemById(String id);
    List<MenuItem> getItemByRestaurantId(String restaurantId);

    List<Tag> getAllTags();
    Tag getTagById(String id);

    List<WorkTiming> getAllWorkTimings();
    WorkTiming getWorkTimingById(String id);

    List<MenuSection> getMenuSectionByRestaurantId(String restaurantId);

    List<MenuCategory> getMenuCategoryByRestaurantId(String restaurantId);

    MenuSection getMenuSectionById(String sectionId);

    MenuCategory getMenuCategoryById(String categoryId);

    Customization getCustomizationById(String customizationId);

    List<Customization> getAllCustomizationsByRestaurantId(String restaurantId);

    List<Tag> getAllTagsByRestaurantId(String restaurantId);
}
