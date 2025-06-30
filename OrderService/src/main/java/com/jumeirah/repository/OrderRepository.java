package com.jumeirah.repository;

import com.jumeirah.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order , UUID> {
    Optional<Order> findByRestaurantIdAndTableNumberAndIsOrderCloseFalse(UUID restaurantUUID, String tableNo);
}
