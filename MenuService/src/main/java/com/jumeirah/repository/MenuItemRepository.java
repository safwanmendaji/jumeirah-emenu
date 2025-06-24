package com.jumeirah.repository;

import com.jumeirah.mondel.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends MongoRepository<MenuItem, UUID> {
    List<MenuItem> findByCategoryId(String categoryId);
}