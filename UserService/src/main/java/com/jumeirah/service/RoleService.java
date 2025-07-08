package com.jumeirah.service;

import com.jumeirah.dto.RoleRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RoleService {

    ResponseEntity<?> createRole(RoleRequestDto roleRequestDto);

    ResponseEntity<?> getRole(UUID id);

    ResponseEntity<?> deleteRole(UUID id);

    ResponseEntity<?> updateRole(UUID id, RoleRequestDto roleRequestDto);

    ResponseEntity<?> getAllRoles(int i, int size, String search);
}
