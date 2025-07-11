package com.jumeirah.controllers;

import com.jumeirah.dto.MenuResponseDTO;
import com.jumeirah.dto.MenuSectionDTO;
import com.jumeirah.dto.MenuSectionReqDto;
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
    @GetMapping("/getMenuById/{menuId}")
    public ResponseEntity<ApiResponse> getMenu(@PathVariable String menuId) {
        MenuResponseDTO menuResponse = menuService.getMenuWithDetails(menuId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get MainMenuBy Id." , menuResponse));
    }

    // 2. Get Menu by Restaurant ID
    @GetMapping("/mainmenu/{restId}")
    public ResponseEntity<ApiResponse> getMenuByRestaurantId(@PathVariable String restId) {
        List<MenuResponseDTO> menuResponse = menuService.getMenuWithDetailsByRestId(restId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get All MainMenuBy RestaurantId." , menuResponse));
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
    @PostMapping("/create/sections")
    public ResponseEntity<MenuSection> createMenuSection(@RequestBody MenuSection menuSection) {
        return ResponseEntity.ok(menuService.createMenuSection(menuSection));
    }

    // 7. Update Menu Section
    @PutMapping("/updateSections/{sectionId}")
    public ResponseEntity<MenuSection> updateMenuSection(@PathVariable String sectionId, @RequestBody MenuSection section) {
        MenuSection updatedSection = menuService.updateMenuSection(sectionId, section);
        return ResponseEntity.ok(updatedSection);
    }

    // 8. Delete Menu Section by ID
    @DeleteMapping("/deleteSections/{sectionId}")
    public ResponseEntity<String> deleteMenuSection(@PathVariable String sectionId) {
        menuService.deleteMenuSection(sectionId);
        return ResponseEntity.ok("Menu Section deleted successfully");
    }

    @GetMapping("/getsections/byrestaurant/{restaurantId}")
    public ResponseEntity<?> getMenuSectionByRestaurantId(@PathVariable String restaurantId) {
        List<MenuSection> menuSectionList = menuService.getMenuSectionByRestaurantId(restaurantId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get Sections By RestaurantId." , menuSectionList));
    }

    @GetMapping("/getsectionsbyid/{sectionId}")
    public ResponseEntity<?> getMenuSectionById(@PathVariable String sectionId) {
        MenuSection menuSection = menuService.getMenuSectionById(sectionId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get Menu SectionById." , menuSection));
    }

    // **CRUD Operations for Menu Category**
    // 9. Create Menu Category
    @PostMapping("/create/categories")
    public ResponseEntity<MenuCategory> createMenuCategory(@RequestBody MenuCategory category) {
        return ResponseEntity.ok(menuService.createMenuCategory(category));
    }

    // 10. Update Menu Category
    @PutMapping("/updateCategories/{categoryId}")
    public ResponseEntity<MenuCategory> updateMenuCategory(@PathVariable String categoryId, @RequestBody MenuCategory category) {
        MenuCategory updatedCategory = menuService.updateMenuCategory(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }

    // 11. Delete Menu Category by ID
    @DeleteMapping("/deleteCategories/{categoryId}")
    public ResponseEntity<String> deleteMenuCategory(@PathVariable String categoryId) {
        menuService.deleteMenuCategory(categoryId);
        return ResponseEntity.ok("Menu Category deleted successfully");
    }

    @GetMapping("/getcategory/byrestaurantid/{restaurantId}")
    public ResponseEntity<?> getMenuCategoryByRestaurantId(@PathVariable String restaurantId) {
        List<MenuCategory> menuCategoriesList = menuService.getMenuCategoryByRestaurantId(restaurantId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get CategoryBy RestaurantId." , menuCategoriesList));
    }

    @GetMapping("/getcategorybyid/{categoryId}")
    public ResponseEntity<?> getMenuCategoryById(@PathVariable String categoryId) {
        MenuCategory menuCategory = menuService.getMenuCategoryById(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get CategoryBy RestaurantId." , menuCategory));
    }


    // **CRUD Operations for Menu Item**
    // 12. Create Menu Item
    @PostMapping("/items")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuService.createMenuItem(item));
    }

    // 13. Update Menu Item
    @PutMapping("/updateItems/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String itemId, @RequestBody MenuItem item) {
        MenuItem updatedItem = menuService.updateMenuItem(itemId, item);
        return ResponseEntity.ok(updatedItem);
    }

    // **CRUD Operations for Customization**
    // 15. Create Customization
    @PostMapping("/create/customizations")
    public ResponseEntity<Customization> createCustomization(@RequestBody Customization customization) {
        return ResponseEntity.ok(menuService.createCustomization(customization));
    }

    // 16. Update Customization
    @PutMapping("/updateCustomizations/{customizationId}")
    public ResponseEntity<Customization> updateCustomization(@PathVariable String customizationId, @RequestBody Customization customization) {
        Customization updatedCustomization = menuService.updateCustomization(customizationId, customization);
        return ResponseEntity.ok(updatedCustomization);
    }

    // 17. Delete Customization by ID
    @DeleteMapping("/deleteCustomizations/{customizationId}")
    public ResponseEntity<String> deleteCustomization(@PathVariable String customizationId) {
        menuService.deleteCustomization(customizationId);
        return ResponseEntity.ok("Customization deleted successfully");
    }


    @GetMapping("/getcustomizationsbyid/{customizationId}")
    public ResponseEntity<?> getCustomizationsById(@PathVariable String customizationId) {
        Customization customization =  menuService.getCustomizationById(customizationId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "CustomizationById get successfully." , customization));
    }

    @GetMapping("/getcustomizations/byrestaurantid/{restaurantId}")
    public ResponseEntity<?> getAllCustomizationsByRestaurantId(@PathVariable String restaurantId) {
        List<Customization> customizationLst =  menuService.getAllCustomizationsByRestaurantId(restaurantId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "CustomizationById get successfully." , customizationLst));
    }

    // GET menu category by ID
    @GetMapping("/getCategoriesById/{categoryId}")
    public ResponseEntity<MenuCategory> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(menuService.getCategoryById(categoryId));
    }

    // **CRUD Operations for Tag**
    // 18. Create Tag
    @PostMapping("/create/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(menuService.createTag(tag));
    }



    // 19. Update Tag
    @PutMapping("/updateTags/{tagId}")
    public ResponseEntity<Tag> updateTag(@PathVariable String tagId, @RequestBody Tag tag) {
        Tag updatedTag = menuService.updateTag(tagId, tag);
        return ResponseEntity.ok(updatedTag);
    }

    // 20. Delete Tag by ID
    @DeleteMapping("/deleteTags/{tagId}")
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
    @PutMapping("/updateWorktimings/{timingId}")
    public ResponseEntity<WorkTiming> updateWorkTiming(@PathVariable String timingId, @RequestBody WorkTiming timing) {
        WorkTiming updatedTiming = menuService.updateWorkTiming(timingId, timing);
        return ResponseEntity.ok(updatedTiming);
    }

    // 23. Delete Work Timing by ID
    @DeleteMapping("/deleteWorktimings/{timingId}")
    public ResponseEntity<String> deleteWorkTiming(@PathVariable String timingId) {
        menuService.deleteWorkTiming(timingId);
        return ResponseEntity.ok("Work Timing deleted successfully");
    }

    @GetMapping("/getAllSections")
    public ResponseEntity<List<MenuSection>> getAllSections() {
        return ResponseEntity.ok(menuService.getAllSections());
    }

    @GetMapping("/getSectionsById/{sectionId}")
    public ResponseEntity<MenuSection> getSectionById(@PathVariable String sectionId) {
        return ResponseEntity.ok(menuService.getSectionById(sectionId));
    }

    // === GET + DELETE for Menu Items ===
    @GetMapping("/getAllItems")
    public ResponseEntity<List<MenuItem>> getAllItems() {
        return ResponseEntity.ok(menuService.getAllItems());
    }

    @GetMapping("/getitembyid/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable String itemId) {
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get ItemBy Id" , menuService.getItemById(itemId)));
    }

    @GetMapping("/getallitems/byrestaurantid/{restaurantId}")
    public ResponseEntity<?> getItemByRestaurantId(@PathVariable String restaurantId) {
        return ResponseEntity.ok(new ApiResponse<>(200 , "Get ItemBy Id" , menuService.getItemByRestaurantId(restaurantId)));
    }

    @DeleteMapping("/deleteItems/{itemId}")
    public ResponseEntity<String> deleteItemById(@PathVariable String itemId) {
        menuService.deleteMenuItem(itemId);
        return ResponseEntity.ok("Menu item deleted successfully");
    }

    // === GET Methods for Tags ===
    @GetMapping("/getalltags/byrestaurantId/{restaurantId}")
    public ResponseEntity<?> getAllTagsByRestaurantId(@PathVariable String restaurantId) {
        List<Tag> tagList = menuService.getAllTagsByRestaurantId(restaurantId);
        return ResponseEntity.ok(new ApiResponse<>(200 , "All TagsBy Restaurant get successfully" , tagList));
    }

    @GetMapping("/gettagbyid/{tagId}")
    public ResponseEntity<?> getTagById(@PathVariable String tagId) {
        return ResponseEntity.ok(new ApiResponse<>(200,"Successfully get TagById." , menuService.getTagById(tagId)));
    }

    // === GET Methods for Work Timings ===
    @GetMapping("/getWorktimings")
    public ResponseEntity<List<WorkTiming>> getAllWorkTimings() {
        return ResponseEntity.ok(menuService.getAllWorkTimings());
    }

    @GetMapping("/getWorktimingsById/{timingId}")
    public ResponseEntity<WorkTiming> getWorkTimingById(@PathVariable String timingId) {
        return ResponseEntity.ok(menuService.getWorkTimingById(timingId));
    }
}



