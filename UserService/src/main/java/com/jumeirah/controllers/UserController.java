package com.jumeirah.controllers;

import com.jumeirah.dto.UserRequestDto;
import com.jumeirah.repository.UserRepository;
import com.jumeirah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/test")
    public String testing(){
        return "Testing";
    }


    @GetMapping("/getUsersByRestaurant/{restaurantId}")
    public ResponseEntity<?> getUsersByRestaurant(@PathVariable UUID restaurantId) {
        return userService.getUsersByRestaurantId(restaurantId);
    }

    @GetMapping("getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(userId, userRequestDto);
    }

    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        return userService.deleteUser(userId);
    }
}
