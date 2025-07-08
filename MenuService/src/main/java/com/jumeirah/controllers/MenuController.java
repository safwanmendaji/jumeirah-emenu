package com.jumeirah.controllers;

import com.jumeirah.dto.MenuResponseDTO;
import com.jumeirah.mondel.*;
import com.jumeirah.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    // Test Endpoint
    @GetMapping("/test")
    public String test(){
        return "Working...";
    }

    // 1. Get Menu by Menu ID
    @GetMapping("getMenuById/{menuId}")
    public ResponseEntity<MenuResponseDTO> getMenu(@PathVariable String menuId) {
        MenuResponseDTO menuResponse = menuService.getMenuWithDetails(menuId);
        return ResponseEntity.ok(menuResponse);
    }

    // 2. Get Menu by Restaurant ID
    @GetMapping("/byrestaurantid/{restId}")
    public ResponseEntity<List<MenuResponseDTO>> getMenuByRestaurantId(@PathVariable String restId) {
        List<MenuResponseDTO> menuResponse = menuService.getMenuWithDetailsByRestId(restId);
        return ResponseEntity.ok(menuResponse);
    }

    // **CRUD Operations for Menu**
    // 3. Create Menu
    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.createMenu(menu));
    }

    // 4. Update Menu
    @PutMapping("/update/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable String menuId, @RequestBody Menu menu) {
        Menu updatedMenu = menuService.updateMenu(menuId, menu);
        return ResponseEntity.ok(updatedMenu);
    }

    // 5. Delete Menu by ID
    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable String menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok("Menu deleted successfully");
    }

    // **CRUD Operations for Menu Section**
    // 6. Create Menu Section
    @PostMapping("create/sections")
    public ResponseEntity<MenuSection> createMenuSection(@RequestBody MenuSection section) {
        return ResponseEntity.ok(menuService.createMenuSection(section));
    }

    // 7. Update Menu Section
    @PutMapping("update/sections/{sectionId}")
    public ResponseEntity<MenuSection> updateMenuSection(@PathVariable String sectionId, @RequestBody MenuSection section) {
        MenuSection updatedSection = menuService.updateMenuSection(sectionId, section);
        return ResponseEntity.ok(updatedSection);
    }

    // 8. Delete Menu Section by ID
    @DeleteMapping("delete/sections/{sectionId}")
    public ResponseEntity<String> deleteMenuSection(@PathVariable String sectionId) {
        menuService.deleteMenuSection(sectionId);
        return ResponseEntity.ok("Menu Section deleted successfully");
    }

    // **CRUD Operations for Menu Category**
    // 9. Create Menu Category
    @PostMapping("create/categories")
    public ResponseEntity<MenuCategory> createMenuCategory(@RequestBody MenuCategory category) {
        return ResponseEntity.ok(menuService.createMenuCategory(category));
    }

    // 10. Update Menu Category
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<MenuCategory> updateMenuCategory(@PathVariable String categoryId, @RequestBody MenuCategory category) {
        MenuCategory updatedCategory = menuService.updateMenuCategory(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }

    // 11. Delete Menu Category by ID
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable String categoryId) {
        menuService.deleteMenuCategory(categoryId);
        return ResponseEntity.ok("Menu Category deleted successfully");
    }

    // **CRUD Operations for Menu Item**
    // 12. Create Menu Item
    @PostMapping("/items")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuService.createMenuItem(item));
    }

    // 13. Update Menu Item
    @PutMapping("/items/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String itemId, @RequestBody MenuItem item) {
        MenuItem updatedItem = menuService.updateMenuItem(itemId, item);
        return ResponseEntity.ok(updatedItem);
    }

    // 14. Delete Menu Item by ID
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable String itemId) {
        menuService.deleteMenuItem(itemId);
        return ResponseEntity.ok("Menu Item deleted successfully");
    }

    // **CRUD Operations for Customization**
    // 15. Create Customization
    @PostMapping("/customizations")
    public ResponseEntity<Customization> createCustomization(@RequestBody Customization customization) {
        return ResponseEntity.ok(menuService.createCustomization(customization));
    }

    // 16. Update Customization
    @PutMapping("/customizations/{customizationId}")
    public ResponseEntity<Customization> updateCustomization(@PathVariable String customizationId, @RequestBody Customization customization) {
        Customization updatedCustomization = menuService.updateCustomization(customizationId, customization);
        return ResponseEntity.ok(updatedCustomization);
    }

    // 17. Delete Customization by ID
    @DeleteMapping("/customizations/{customizationId}")
    public ResponseEntity<String> deleteCustomization(@PathVariable String customizationId) {
        menuService.deleteCustomization(customizationId);
        return ResponseEntity.ok("Customization deleted successfully");
    }

    // **CRUD Operations for Tag**
    // 18. Create Tag
    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(menuService.createTag(tag));
    }

    // 19. Update Tag
    @PutMapping("/tags/{tagId}")
    public ResponseEntity<Tag> updateTag(@PathVariable String tagId, @RequestBody Tag tag) {
        Tag updatedTag = menuService.updateTag(tagId, tag);
        return ResponseEntity.ok(updatedTag);
    }

    // 20. Delete Tag by ID
    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable String tagId) {
        menuService.deleteTag(tagId);
        return ResponseEntity.ok("Tag deleted successfully");
    }

    // **CRUD Operations for Work Timing**
    // 21. Create Work Timing
    @PostMapping("/worktimings")
    public ResponseEntity<WorkTiming> createWorkTiming(@RequestBody WorkTiming timing) {
        return ResponseEntity.ok(menuService.createWorkTiming(timing));
    }

    // 22. Update Work Timing
    @PutMapping("/worktimings/{timingId}")
    public ResponseEntity<WorkTiming> updateWorkTiming(@PathVariable String timingId, @RequestBody WorkTiming timing) {
        WorkTiming updatedTiming = menuService.updateWorkTiming(timingId, timing);
        return ResponseEntity.ok(updatedTiming);
    }

    // 23. Delete Work Timing by ID
    @DeleteMapping("/worktimings/{timingId}")
    public ResponseEntity<String> deleteWorkTiming(@PathVariable String timingId) {
        menuService.deleteWorkTiming(timingId);
        return ResponseEntity.ok("Work Timing deleted successfully");
    }
}
