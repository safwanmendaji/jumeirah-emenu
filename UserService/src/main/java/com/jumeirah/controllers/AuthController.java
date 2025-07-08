package com.jumeirah.controllers;

import com.jumeirah.dto.LoginRequestDto;
import com.jumeirah.jwt.JwtService;
;
import com.jumeirah.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        try {
            // Just parse the token to check validity
            String userId = jwtService.extractUsername(token); // or extractUserId(token) based on your logic

            // Optionally, check if the token is expired
            if (!jwtService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is invalid or expired");
            }

            return ResponseEntity.ok("Token is valid");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token: " + e.getMessage());
        }
    }
}
