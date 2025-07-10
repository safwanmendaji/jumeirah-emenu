package com.jumeirah.serviceimpl;

import com.jumeirah.dto.*;
import com.jumeirah.jwt.JwtService;
import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.UserInfo;
import com.jumeirah.repository.UserRepository;
import com.jumeirah.service.AuthService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public ResponseEntity<?> login(LoginRequestDto request) {
        Optional<UserInfo> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(400, "User not found", null));
        }

        UserInfo user = optionalUser.get();

        // Verify the encrypted password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(400, "Invalid credentials", null));
        }

        // Generate JWT
        String token = jwtService.generateTokenWithClaims(user);
        UserDto userDto = mapToUserDto(user);
        AuthResponse authResponse = new AuthResponse(token, userDto);

        return ResponseEntity.ok(new ApiResponse<>(200, "Login successful", authResponse));
    }

    private UserDto mapToUserDto(UserInfo user) {
        RoleDto roleDto = null;
        if (user.getRole() != null) {
            roleDto = new RoleDto(
                    user.getRole().getId(),
                    user.getRole().getRoleName()
            );
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                roleDto,
                user.getRestaurantId()
        );
    }

}
