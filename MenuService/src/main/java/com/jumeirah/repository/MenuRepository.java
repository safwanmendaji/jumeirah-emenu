package com.jumeirah.repository;

import com.jumeirah.mondel.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends MongoRepository<Menu, UUID> {
    List<Menu> findByRestaurantId(String restaurantId);
}