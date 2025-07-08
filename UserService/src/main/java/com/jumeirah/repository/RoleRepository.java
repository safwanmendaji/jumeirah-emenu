package com.jumeirah.repository;

import com.jumeirah.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends MongoRepository<Role, UUID> {
    Optional<Role> findByRoleName(String roleName);

    Page<Role> findByRoleNameContainingIgnoreCase(String search, Pageable pageable);
}
