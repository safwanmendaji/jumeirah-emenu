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

//                    // Fetch customizations
//                    if (item.getCustomizationIds() != null && !item.getCustomizationIds().isEmpty()) {
//                        List<Customization> customizations = customizationRepository.findAllByCustomazationId(item.getCustomizationIds().toString());
//                        itemDTO.setCustomizations(customizations.stream().map(cust -> {
//                            CustomizationDTO custDTO = new CustomizationDTO();
//                            custDTO.setName(cust.getName());
//                            custDTO.setDescription(cust.getDescription());
//                            custDTO.setMin(cust.getMin());
//                            custDTO.setMax(cust.getMax());
//                            custDTO.setDefaultValue(cust.getDefaultValue());
//                            custDTO.setList(cust.getList().stream().map(opt -> {
//                                CustomizationOptionDTO optDTO = new CustomizationOptionDTO();
//                                optDTO.setName(opt.getName());
//                                optDTO.setPrice(opt.getPrice());
//                                optDTO.setAltName(opt.getAltName());
//                                return optDTO;
//                            }).collect(Collectors.toList()));
//                            return custDTO;
//                        }).collect(Collectors.toList()));
//                    }
//
//                    // Fetch tags
//                    if (item.getTagIds() != null && !item.getTagIds().isEmpty()) {
//                        List<Tag> tags = tagRepository.findAllByTagId(item.getTagIds().toString());
//                        itemDTO.setTags(tags.stream().map(tag -> {
//                            TagDTO tagDTO = new TagDTO();
//                            tagDTO.setTagId(tag.getTagId().toString());
//                            tagDTO.setTitle(tag.getTitle());
//                            tagDTO.setDisplay(tag.isDisplay());
//                            tagDTO.setCode(tag.getCode());
//                            tagDTO.setFilter(tag.isFilter());
//                            if (tag.getIcon() != null) {
//                                IconDTO iconDTO = new IconDTO();
//                                iconDTO.setSize(tag.getIcon().getSize());
//                                iconDTO.setSrc(tag.getIcon().getSrc());
//                                iconDTO.setAlt(tag.getIcon().getAlt());
//                                iconDTO.setName(tag.getIcon().getName());
//                                tagDTO.setIcon(iconDTO);
//                            }
//                            tagDTO.setType(tag.getType());
//                            return tagDTO;
//                        }).collect(Collectors.toList()));
//                    }

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

// Fetch tags
                    if (item.getTagIds() != null && !item.getTagIds().isEmpty()) {
                        List<UUID> tagUUIDs = item.getTagIds().stream()
                                .map(UUID::fromString)
                                .collect(Collectors.toList());

                        List<Tag> tags = tagRepository.findAllById(tagUUIDs);

                        itemDTO.setTags(tags.stream().map(tag -> {
                            TagDTO tagDTO = new TagDTO();
                            tagDTO.setTagId(tag.getTagId().toString());
                            tagDTO.setTitle(tag.getTitle());
                            tagDTO.setDisplay(tag.isDisplay());
                            tagDTO.setCode(tag.getCode());
                            tagDTO.setFilter(tag.isFilter());

                            if (tag.getIcon() != null) {
                                IconDTO iconDTO = new IconDTO();
                                iconDTO.setSize(tag.getIcon().getSize());
                                iconDTO.setSrc(tag.getIcon().getSrc());
                                iconDTO.setAlt(tag.getIcon().getAlt());
                                iconDTO.setName(tag.getIcon().getName());
                                tagDTO.setIcon(iconDTO);
                            }

                            tagDTO.setType(tag.getType());
                            return tagDTO;
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
            response.setMenuName(menu.getMenuName());

            List<MenuSection> sections = menuSectionRepository.findByMenuId(menu.getMenuId().toString());
            response.setMenuSections(sections.stream().map(section -> {
                MenuSectionDTO sectionDTO = new MenuSectionDTO();
                sectionDTO.setSectionName(section.getSectionName());

                List<MenuCategory> categories = menuCategoryRepository.findBySectionId(section.getMenuSectionId().toString());
                sectionDTO.setMenuCategories(categories.stream().map(category -> {
                    MenuCategoryDTO categoryDTO = new MenuCategoryDTO();
                    categoryDTO.setName(category.getName());

                    List<MenuItem> items = menuItemRepository.findByCategoryId(category.getMenuCategoryId().toString());
                    categoryDTO.setItems(items.stream().map(item -> {
                        MenuItemDTO itemDTO = new MenuItemDTO();
                        itemDTO.setName(item.getName());
                        itemDTO.setDescription(item.getDescription());
                        itemDTO.setPrice(item.getPrice());
                        itemDTO.setImage(item.getImage());
                        itemDTO.setCommentCode(item.getCommentCode());

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

                        if (item.getTagIds() != null && !item.getTagIds().isEmpty()) {
                            List<UUID> tagUUIDs = item.getTagIds().stream()
                                    .map(UUID::fromString)
                                    .collect(Collectors.toList());

                            List<Tag> tags = tagRepository.findAllById(tagUUIDs);

                            itemDTO.setTags(tags.stream().map(tag -> {
                                TagDTO tagDTO = new TagDTO();
                                tagDTO.setTagId(tag.getTagId().toString());
                                tagDTO.setTitle(tag.getTitle());
                                tagDTO.setDisplay(tag.isDisplay());
                                tagDTO.setCode(tag.getCode());
                                tagDTO.setFilter(tag.isFilter());

                                if (tag.getIcon() != null) {
                                    IconDTO iconDTO = new IconDTO();
                                    iconDTO.setSize(tag.getIcon().getSize());
                                    iconDTO.setSrc(tag.getIcon().getSrc());
                                    iconDTO.setAlt(tag.getIcon().getAlt());
                                    iconDTO.setName(tag.getIcon().getName());
                                    tagDTO.setIcon(iconDTO);
                                }

                                tagDTO.setType(tag.getType());
                                return tagDTO;
                            }).collect(Collectors.toList()));
                        }

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

    @Override
    public Menu createMenu(Menu menu) {
        if (menu == null || menu.getMenuName() == null || menu.getRestaurantId() == null) {
            throw new IllegalArgumentException("Menu name and restaurant ID are required");
        }
        return menuRepository.save(menu);
    }

    @Override
    public MenuSection createMenuSection(MenuSection section) {
        if (section == null || section.getMenuId() == null || section.getSectionName() == null) {
            throw new IllegalArgumentException("Menu ID and section name are required");
        }
        return menuSectionRepository.save(section);
    }

    @Override
    public MenuCategory createMenuCategory(MenuCategory category) {
        if (category == null || category.getSectionId() == null || category.getName() == null) {
            throw new IllegalArgumentException("Section ID and category name are required");
        }
        return menuCategoryRepository.save(category);
    }

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        if (item == null || item.getCategoryId() == null || item.getName() == null) {
            throw new IllegalArgumentException("Category ID and item name are required");
        }
        return menuItemRepository.save(item);
    }

    @Override
    public Customization createCustomization(Customization customization) {
        if (customization == null || customization.getName() == null) {
            throw new IllegalArgumentException("Customization name is required");
        }
        return customizationRepository.save(customization);
    }

    @Override
    public Tag createTag(Tag tag) {
        if (tag == null || tag.getTitle() == null) {
            throw new IllegalArgumentException("Tag title is required");
        }
        return tagRepository.save(tag);
    }

    @Override
    public WorkTiming createWorkTiming(WorkTiming timing) {
        if (timing == null || timing.getMenuId() == null || timing.getOpenTime() == null || timing.getCloseTime() == null) {
            throw new IllegalArgumentException("Menu ID, open time, and close time are required");
        }
        return workTimingRepository.save(timing);
    }
}