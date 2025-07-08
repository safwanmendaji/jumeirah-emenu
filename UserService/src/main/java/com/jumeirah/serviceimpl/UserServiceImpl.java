package com.jumeirah.serviceimpl;

import com.jumeirah.dto.LoginRequestDto;
import com.jumeirah.dto.UserDto;
import com.jumeirah.dto.UserRequestDto;
import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.Role;
import com.jumeirah.model.UserInfo;
import com.jumeirah.repository.RoleRepository;
import com.jumeirah.repository.UserRepository;
import com.jumeirah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Override
        public ResponseEntity<?> createUser(UserRequestDto userRequestDto) {
            // Validate required fields
            if (userRequestDto.getEmail() == null || userRequestDto.getPassword() == null || userRequestDto.getRoleId() == null) {
                ApiResponse<String> response = new ApiResponse<>(400, "Email, password, and roleId are required", null);
                return ResponseEntity.badRequest().body(response);
            }

            // Check if user already exists with the given email
            Optional<UserInfo> existingUser = userRepository.findByEmail(userRequestDto.getEmail());
            if (existingUser.isPresent()) {
                ApiResponse<String> response = new ApiResponse<>(400, "User already exists with this email", null);
                return ResponseEntity.badRequest().body(response);
            }

            // Check if the role exists
            Optional<Role> role = roleRepository.findById(userRequestDto.getRoleId());
            if (!role.isPresent()) {
                ApiResponse<String> response = new ApiResponse<>(404, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            // Create new user
            UserInfo user = new UserInfo();
            user.setId(UUID.randomUUID());
            user.setName(userRequestDto.getName());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            user.setMobileNo(userRequestDto.getMobileNo());
            user.setAddress(userRequestDto.getAddress());
            user.setRole(role.get());

            UserInfo savedUser = userRepository.save(user);

            ApiResponse<UserInfo> response = new ApiResponse<>(201, "User created successfully", savedUser);
            return ResponseEntity.status(201).body(response);
        }


}

