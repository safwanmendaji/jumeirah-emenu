package com.jumeirah.controllers;

import com.jumeirah.dto.RoleRequestDto;
import com.jumeirah.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleRequestDto roleRequestDto){
        return roleService.createRole(roleRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable UUID id) {
        return roleService.getRole(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable UUID id, @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.updateRole(id, roleRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable UUID id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String search) {
        return roleService.getAllRoles(page - 1, size, search); // page - 1 to start from page 1
    }






}
