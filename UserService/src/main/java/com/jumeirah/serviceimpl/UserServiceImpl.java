package com.jumeirah.serviceimpl;

import com.jumeirah.dto.UserRequestDto;
import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.Role;
import com.jumeirah.model.UserInfo;
import com.jumeirah.repository.RoleRepository;
import com.jumeirah.repository.UserRepository;
import com.jumeirah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
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


    @Autowired
    private RestTemplate restTemplate;


        @Override
        public ResponseEntity<?> createUser(UserRequestDto dto) {
            // Basic field validation...
            if (dto.getEmail() == null || dto.getPassword() == null || dto.getRoleId() == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(400, "Email, password, and roleId are required", null));
            }

//            // Validate restaurantId
//            if (dto.getRestaurantId() == null || !isValidRestaurant(dto.getRestaurantId())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ApiResponse<>(400, "Invalid or missing restaurantId", null));
//            }

            if (dto.getRestaurantId() != null) {
                boolean valid = isValidRestaurant(dto.getRestaurantId());
                if (!valid) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ApiResponse<>(400, "Invalid restaurantId provided", null));
                }
            }

            // Check if user already exists
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(400, "User already exists with this email", null));
            }

            // Check if the role exists
            Optional<Role> role = roleRepository.findById(dto.getRoleId());
            if (role.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Role not found", null));
            }

            // Create user
            UserInfo user = new UserInfo();
            user.setId(UUID.randomUUID());
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setMobileNo(dto.getMobileNo());
            user.setAddress(dto.getAddress());
            user.setRole(role.get());
            user.setRestaurantId(dto.getRestaurantId());

            UserInfo savedUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "User created successfully", savedUser));
        }



    @Override
    public ResponseEntity<?> getUsersByRestaurantId(UUID restaurantId) {
        List<UserInfo> users = userRepository.findByRestaurantId(restaurantId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Users fetched", users));
    }

    @Override
    public ResponseEntity<?> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(new ApiResponse<>(200, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "User not found", null)));
    }

    @Override
    public ResponseEntity<?> updateUser(UUID userId, UserRequestDto dto) {
        Optional<UserInfo> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "User not found", null));
        }

        UserInfo user = optionalUser.get();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobileNo(dto.getMobileNo());
        user.setAddress(dto.getAddress());
        user.setRestaurantId(dto.getRestaurantId());

        if (dto.getRoleId() != null) {
            roleRepository.findById(dto.getRoleId()).ifPresent(user::setRole);
        }

        user.setUpdatedAt(new Date());
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse<>(200, "User updated", user));
    }

    @Override
    public ResponseEntity<?> deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "User not found", null));
        }

        userRepository.deleteById(userId);
        return ResponseEntity.ok(new ApiResponse<>(200, "User deleted", null));
    }

    private boolean isValidRestaurant(UUID restaurantId) {
        String url = "http://localhost:9091/api/restaurant/getById/" + restaurantId;
        try {
            ResponseEntity<ApiResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    ApiResponse.class
            );
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().getData() != null;
        } catch (HttpClientErrorException.NotFound e) {
            return false;  // Restaurant does not exist
        } catch (Exception e) {
            throw new IllegalStateException("Unable to validate restaurantId from Restaurant Service", e);
        }
    }


}

