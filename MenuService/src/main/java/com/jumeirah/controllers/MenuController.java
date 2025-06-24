package com.jumeirah.controllers;


import com.jumeirah.dto.MenuResponseDTO;
import com.jumeirah.mondel.*;
import com.jumeirah.service.MenuService;
import jakarta.ws.rs.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/test")
    public String test(){
        return "Working...";
    }
    // Get full menu with details
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> getMenu(@PathVariable String menuId) {
        MenuResponseDTO menuResponse = menuService.getMenuWithDetails(menuId);
        return ResponseEntity.ok(menuResponse);
    }

    @GetMapping("/byrestaurantid/{restId}")
    public ResponseEntity<List<MenuResponseDTO>> getMenuByRestaurantId(@PathVariable String restId) {
        List<MenuResponseDTO> menuResponse = menuService.getMenuWithDetailsByRestId(restId);
        return ResponseEntity.ok(menuResponse);
    }

    // Create Menu
    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu( @RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.createMenu(menu));
    }

    // Create Menu Section
    @PostMapping("/sections")
    public ResponseEntity<MenuSection> createMenuSection(@RequestBody MenuSection section) {
        return ResponseEntity.ok(menuService.createMenuSection(section));
    }

    // Create Menu Category
    @PostMapping("/categories")
    public ResponseEntity<MenuCategory> createMenuCategory(@RequestBody MenuCategory category) {
        return ResponseEntity.ok(menuService.createMenuCategory(category));
    }

    // Create Menu Item
    @PostMapping("/items")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuService.createMenuItem(item));
    }

    // Create Customization
    @PostMapping("/customizations")
    public ResponseEntity<Customization> createCustomization(@RequestBody Customization customization) {
        return ResponseEntity.ok(menuService.createCustomization(customization));
    }

    // Create Tag
    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag( @RequestBody Tag tag) {
        return ResponseEntity.ok(menuService.createTag(tag));
    }

    // Create Work Timing
    @PostMapping("/worktimings")
    public ResponseEntity<WorkTiming> createWorkTiming(@RequestBody WorkTiming timing) {
        return ResponseEntity.ok(menuService.createWorkTiming(timing));
    }

}