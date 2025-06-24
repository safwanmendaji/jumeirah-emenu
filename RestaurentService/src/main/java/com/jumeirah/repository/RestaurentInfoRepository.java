package com.jumeirah.repository;

import com.jumeirah.model.RestaurentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurentInfoRepository extends MongoRepository<RestaurentInfo, UUID> {
    Optional<RestaurentInfo> findByRestaurantCode(String restaurantCode);
    boolean existsByRestaurantCode(String restaurantCode);
}
