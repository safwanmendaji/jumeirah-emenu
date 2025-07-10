package com.jumeirah.repository;

import com.jumeirah.mondel.MenuCategory;
import com.jumeirah.mondel.MenuSection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MenuCategoryRepository extends MongoRepository<MenuCategory, UUID> {
    List<MenuCategory> findBySectionId(String sectionId);

    List<MenuCategory> findByRestaurantId(String restaurantId);
}