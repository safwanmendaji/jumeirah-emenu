package com.jumeirah.repository;

import com.jumeirah.mondel.Customization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface CustomizationRepository extends MongoRepository<Customization, UUID> {
    List<Customization> findAllByCustomazationId(String toString);
}