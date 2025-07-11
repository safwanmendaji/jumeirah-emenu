package com.jumeirah.repository;

import com.jumeirah.mondel.Customization;
import com.jumeirah.mondel.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends MongoRepository<Tag, UUID> {


    List<Tag> findByRestaurantId(String restaurantId);
}