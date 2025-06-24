package com.jumeirah.repository;

import com.jumeirah.mondel.WorkTiming;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface WorkTimingRepository extends MongoRepository<WorkTiming, UUID> {
    List<WorkTiming> findByMenuId(String menuId);
}