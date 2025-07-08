package com.jumeirah.serviceimpl;

import com.jumeirah.dto.RoleRequestDto;
import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.Role;
import com.jumeirah.repository.RoleRepository;
import com.jumeirah.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public ResponseEntity<?> createRole(RoleRequestDto roleRequestDto) {

      Optional<Role> existingRole = roleRepository.findByRoleName(roleRequestDto.getRoleName());

      if(existingRole.isPresent()){
          ApiResponse<String> response = new ApiResponse<>(400,"Role already exists with this name "+roleRequestDto.getRoleName(),null);
          return ResponseEntity.badRequest().body(response);
      }

      Role role = new Role();
      role.setRoleName(roleRequestDto.getRoleName());

      Role savedRole = roleRepository.save(role);

        ApiResponse<Role> response = new ApiResponse<Role>(
                200,
                "Role created successfully",
                savedRole
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getRole(UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Role fetched successfully", role.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Role not found", null));
        }
    }

    @Override
    public ResponseEntity<?> updateRole(UUID id, RoleRequestDto roleRequestDto) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (!existingRole.isPresent()) {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Role not found", null));
        }
        Role role = existingRole.get();
        role.setRoleName(roleRequestDto.getRoleName());
        Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(new ApiResponse<>(200, "Role updated successfully", updatedRole));
    }

    @Override
    public ResponseEntity<?> deleteRole(UUID id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Role not found", null));
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Role deleted successfully", null));
    }

    @Override
    public ResponseEntity<ApiResponse<Page<Role>>> getAllRoles(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Role> rolesPage;

        if (search != null && !search.trim().isEmpty()) {
            rolesPage = roleRepository.findByRoleNameContainingIgnoreCase(search, pageable);
        } else {
            rolesPage = roleRepository.findAll(pageable);
        }

        ApiResponse<Page<Role>> response = new ApiResponse<>(200, "Roles fetched with pagination", rolesPage);
        return ResponseEntity.ok(response);
    }


}
