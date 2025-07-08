package com.jumeirah.service.impl;

import com.jumeirah.dto.*;
import com.jumeirah.mondel.*;
import com.jumeirah.repository.*;
import com.jumeirah.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuSectionRepository menuSectionRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemRepository menuItemRepository;
    private final CustomizationRepository customizationRepository;
    private final TagRepository tagRepository;
    private final WorkTimingRepository workTimingRepository;

    // **Get Menu by Menu ID**
    @Override
    public MenuResponseDTO getMenuWithDetails(String menuId) {
        if (menuId == null || menuId.isBlank()) {
            throw new IllegalArgumentException("Menu ID cannot be null or empty");
        }

        Menu menu = menuRepository.findById(UUID.fromString(menuId))
                .orElseThrow(() -> new RuntimeException("Menu not found with ID: " + menuId));

        MenuResponseDTO response = new MenuResponseDTO();
        response.setMenuName(menu.getMenuName());

        // Fetch sections
        List<MenuSection> sections = menuSectionRepository.findByMenuId(menuId);
        response.setMenuSections(sections.stream().map(section -> {
            MenuSectionDTO sectionDTO = new MenuSectionDTO();
            sectionDTO.setSectionName(section.getSectionName());

            // Fetch categories
            List<MenuCategory> categories = menuCategoryRepository.findBySectionId(section.getMenuSectionId().toString());
            sectionDTO.setMenuCategories(categories.stream().map(category -> {
                MenuCategoryDTO categoryDTO = new MenuCategoryDTO();
                categoryDTO.setName(category.getName());

                // Fetch items
                List<MenuItem> items = menuItemRepository.findByCategoryId(category.getMenuCategoryId().toString());
                categoryDTO.setItems(items.stream().map(item -> {
                    MenuItemDTO itemDTO = new MenuItemDTO();
                    itemDTO.setName(item.getName());
                    itemDTO.setDescription(item.getDescription());
                    itemDTO.setPrice(item.getPrice());
                    itemDTO.setImage(item.getImage());
                    itemDTO.setCommentCode(item.getCommentCode());

                    // Fetch customizations
                    if (item.getCustomizationIds() != null && !item.getCustomizationIds().isEmpty()) {
                        List<UUID> customizationUUIDs = item.getCustomizationIds().stream()
                                .map(UUID::fromString)
                                .collect(Collectors.toList());

                        List<Customization> customizations = customizationRepository.findAllById(customizationUUIDs);

                        itemDTO.setCustomizations(customizations.stream().map(cust -> {
                            CustomizationDTO custDTO = new CustomizationDTO();
                            custDTO.setName(cust.getName());
                            custDTO.setDescription(cust.getDescription());
                            custDTO.setMin(cust.getMin());
                            custDTO.setMax(cust.getMax());
                            custDTO.setDefaultValue(cust.getDefaultValue());

                            if (cust.getList() != null) {
                                custDTO.setList(cust.getList().stream().map(opt -> {
                                    CustomizationOptionDTO optDTO = new CustomizationOptionDTO();
                                    optDTO.setName(opt.getName());
                                    optDTO.setPrice(opt.getPrice());
                                    optDTO.setAltName(opt.getAltName());
                                    return optDTO;
                                }).collect(Collectors.toList()));
                            }

                            return custDTO;
                        }).collect(Collectors.toList()));
                    }

                    return itemDTO;
                }).collect(Collectors.toList()));

                return categoryDTO;
            }).collect(Collectors.toList()));

            return sectionDTO;
        }).collect(Collectors.toList()));

        // Fetch work timings
        response.setWorkTimings(workTimingRepository.findByMenuId(menuId));

        return response;
    }

    // **Get Menu by Restaurant ID**
    @Override
    public List<MenuResponseDTO> getMenuWithDetailsByRestId(String restId) {
        if (restId == null || restId.isBlank()) {
            throw new IllegalArgumentException("restId cannot be null or empty");
        }
        System.out.println("ID+====== " + restId);
        List<Menu> menus = menuRepository.findByRestaurantId(restId);
        System.out.println("Size===>>> " + menus.size());

        List<MenuResponseDTO> responseList = new ArrayList<>();

        for (Menu menu : menus) {
            MenuResponseDTO response = new MenuResponseDTO();
            response.setMenuId(menu.getMenuId());
            response.setMenuName(menu.getMenuName());

            List<MenuSection> sections = menuSectionRepository.findByMenuId(menu.getMenuId().toString());
            response.setMenuSections(sections.stream().map(section -> {
                MenuSectionDTO sectionDTO = new MenuSectionDTO();
                sectionDTO.setMenuSectionId(section.getMenuSectionId());
                sectionDTO.setSectionName(section.getSectionName());

                List<MenuCategory> categories = menuCategoryRepository.findBySectionId(section.getMenuSectionId().toString());
                sectionDTO.setMenuCategories(categories.stream().map(category -> {
                    MenuCategoryDTO categoryDTO = new MenuCategoryDTO();
                    categoryDTO.setMenuCategoryId(category.getMenuCategoryId());
                    categoryDTO.setName(category.getName());

                    List<MenuItem> items = menuItemRepository.findByCategoryId(category.getMenuCategoryId().toString());
                    categoryDTO.setItems(items.stream().map(item -> {
                        MenuItemDTO itemDTO = new MenuItemDTO();
                        itemDTO.setMenuItemId(item.getMenuItem());
                        itemDTO.setName(item.getName());
                        itemDTO.setDescription(item.getDescription());
                        itemDTO.setPrice(item.getPrice());
                        itemDTO.setImage(item.getImage());
                        itemDTO.setCommentCode(item.getCommentCode());
                        return itemDTO;
                    }).collect(Collectors.toList()));

                    return categoryDTO;
                }).collect(Collectors.toList()));

                return sectionDTO;
            }).collect(Collectors.toList()));

            response.setWorkTimings(workTimingRepository.findByMenuId(menu.getMenuId().toString()));

            responseList.add(response);
        }

        return responseList;
    }

    // **Create Menu**
    @Override
    public Menu createMenu(Menu menu) {
        if (menu == null || menu.getMenuName() == null || menu.getRestaurantId() == null) {
            throw new IllegalArgumentException("Menu name and restaurant ID are required");
        }
        return menuRepository.save(menu);
    }

    // **Create Menu Section**
    @Override
    public MenuSection createMenuSection(MenuSection section) {
        if (section == null || section.getMenuId() == null || section.getSectionName() == null) {
            throw new IllegalArgumentException("Menu ID and section name are required");
        }
        return menuSectionRepository.save(section);
    }

    // **Create Menu Category**
    @Override
    public MenuCategory createMenuCategory(MenuCategory category) {
        if (category == null || category.getSectionId() == null || category.getName() == null) {
            throw new IllegalArgumentException("Section ID and category name are required");
        }
        return menuCategoryRepository.save(category);
    }

    // **Create Menu Item**
    @Override
    public MenuItem createMenuItem(MenuItem item) {
        if (item == null || item.getCategoryId() == null || item.getName() == null) {
            throw new IllegalArgumentException("Category ID and item name are required");
        }
        return menuItemRepository.save(item);
    }

    // **Create Customization**
    @Override
    public Customization createCustomization(Customization customization) {
        if (customization == null || customization.getName() == null) {
            throw new IllegalArgumentException("Customization name is required");
        }
        return customizationRepository.save(customization);
    }

    // **Create Tag**
    @Override
    public Tag createTag(Tag tag) {
        if (tag == null || tag.getTitle() == null) {
            throw new IllegalArgumentException("Tag title is required");
        }
        return tagRepository.save(tag);
    }

    // **Create Work Timing**
    @Override
    public WorkTiming createWorkTiming(WorkTiming timing) {
        if (timing == null || timing.getMenuId() == null || timing.getOpenTime() == null || timing.getCloseTime() == null) {
            throw new IllegalArgumentException("Menu ID, open time, and close time are required");
        }
        return workTimingRepository.save(timing);
    }

    // **Update Menu**
    @Override
    public Menu updateMenu(String menuId, Menu updatedMenu) {
        Menu menu = menuRepository.findById(UUID.fromString(menuId))
                .orElseThrow(() -> new RuntimeException("Menu not found with ID: " + menuId));

        // Update non-null fields
        if (updatedMenu.getMenuName() != null) {
            menu.setMenuName(updatedMenu.getMenuName());
        }

        return menuRepository.save(menu);
    }

    // **Update Menu Section**
    @Override
    public MenuSection updateMenuSection(String sectionId, MenuSection updatedSection) {
        MenuSection section = menuSectionRepository.findById(UUID.fromString(sectionId))
                .orElseThrow(() -> new RuntimeException("Menu section not found with ID: " + sectionId));

        // Update non-null fields
        if (updatedSection.getSectionName() != null) {
            section.setSectionName(updatedSection.getSectionName());
        }

        return menuSectionRepository.save(section);
    }

    // **Update Menu Category**
    @Override
    public MenuCategory updateMenuCategory(String categoryId, MenuCategory updatedCategory) {
        MenuCategory category = menuCategoryRepository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new RuntimeException("Menu category not found with ID: " + categoryId));

        // Update non-null fields
        if (updatedCategory.getName() != null) {
            category.setName(updatedCategory.getName());
        }

        return menuCategoryRepository.save(category);
    }

    // **Update Menu Item**
    @Override
    public MenuItem updateMenuItem(String itemId, MenuItem updatedItem) {
        MenuItem item = menuItemRepository.findById(UUID.fromString(itemId))
                .orElseThrow(() -> new RuntimeException("Menu item not found with ID: " + itemId));

        // Update non-null fields
        if (updatedItem.getName() != null) {
            item.setName(updatedItem.getName());
        }
        if (updatedItem.getDescription() != null) {
            item.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getPrice() != null) {
            item.setPrice(updatedItem.getPrice());
        }

        return menuItemRepository.save(item);
    }

    // **Update Customization**
    @Override
    public Customization updateCustomization(String customizationId, Customization updatedCustomization) {
        Customization customization = customizationRepository.findById(UUID.fromString(customizationId))
                .orElseThrow(() -> new RuntimeException("Customization not found with ID: " + customizationId));

        // Update non-null fields
        if (updatedCustomization.getName() != null) {
            customization.setName(updatedCustomization.getName());
        }
        if (updatedCustomization.getDescription() != null) {
            customization.setDescription(updatedCustomization.getDescription());
        }

        return customizationRepository.save(customization);
    }

    // **Update Tag**
    @Override
    public Tag updateTag(String tagId, Tag updatedTag) {
        Tag tag = tagRepository.findById(UUID.fromString(tagId))
                .orElseThrow(() -> new RuntimeException("Tag not found with ID: " + tagId));

        // Update non-null fields
        if (updatedTag.getTitle() != null) {
            tag.setTitle(updatedTag.getTitle());
        }

        return tagRepository.save(tag);
    }

    // **Update Work Timing**
    @Override
    public WorkTiming updateWorkTiming(String timingId, WorkTiming updatedTiming) {
        WorkTiming timing = workTimingRepository.findById(UUID.fromString(timingId))
                .orElseThrow(() -> new RuntimeException("Work timing not found with ID: " + timingId));

        // Update non-null fields
        if (updatedTiming.getOpenTime() != null) {
            timing.setOpenTime(updatedTiming.getOpenTime());
        }
        if (updatedTiming.getCloseTime() != null) {
            timing.setCloseTime(updatedTiming.getCloseTime());
        }

        return workTimingRepository.save(timing);
    }

    // **Delete Menu**
    @Override
    public void deleteMenu(String menuId) {
        menuRepository.deleteById(UUID.fromString(menuId));
    }

    // **Delete Menu Section**
    @Override
    public void deleteMenuSection(String sectionId) {
        menuSectionRepository.deleteById(UUID.fromString(sectionId));
    }

    // **Delete Menu Category**
    @Override
    public void deleteMenuCategory(String categoryId) {
        menuCategoryRepository.deleteById(UUID.fromString(categoryId));
    }

    // **Delete Menu Item**
    @Override
    public void deleteMenuItem(String itemId) {
        menuItemRepository.deleteById(UUID.fromString(itemId));
    }

    // **Delete Customization**
    @Override
    public void deleteCustomization(String customizationId) {
        customizationRepository.deleteById(UUID.fromString(customizationId));
    }

    // **Delete Tag**
    @Override
    public void deleteTag(String tagId) {
        tagRepository.deleteById(UUID.fromString(tagId));
    }

    // **Delete Work Timing**
    @Override
    public void deleteWorkTiming(String timingId) {
        workTimingRepository.deleteById(UUID.fromString(timingId));
    }
}
