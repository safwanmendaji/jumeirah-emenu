package com.jumeirah.repository;

import com.jumeirah.model.OrderItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsRepository extends MongoRepository<OrderItems , UUID> {
}
