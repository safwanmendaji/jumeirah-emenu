package com.jumeirah.service;

import com.jumeirah.dto.LoginRequestDto;
import com.jumeirah.dto.UserDto;
import com.jumeirah.dto.UserRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<?> createUser(UserRequestDto userRequestDto , HttpServletRequest httpServletRequest);

    ResponseEntity<?> getUsersByRestaurantId(UUID restaurantId);

    ResponseEntity<?> getUserById(UUID userId);

    ResponseEntity<?> updateUser(UUID userId, UserRequestDto dto);

    ResponseEntity<?> deleteUser(UUID userId);
}
