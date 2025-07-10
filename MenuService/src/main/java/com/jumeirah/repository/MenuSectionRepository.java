package com.jumeirah.repository;

import com.jumeirah.mondel.MenuSection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MenuSectionRepository extends MongoRepository<MenuSection, UUID> {
    List<MenuSection> findByMenuId(String menuId);

    List<MenuSection> findByRestaurantId(String restaurantId);
}