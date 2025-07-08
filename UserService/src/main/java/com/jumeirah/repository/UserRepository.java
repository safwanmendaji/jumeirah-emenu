package com.jumeirah.repository;

import com.jumeirah.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends MongoRepository<UserInfo, UUID> {
    Optional<UserInfo> findByEmail(String email);

    UserInfo findByEmailAndPassword(String email, String password);
}
