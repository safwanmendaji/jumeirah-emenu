package com.jumeirah.controllers;


import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.RestaurentInfo;
import com.jumeirah.service.RestaurentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurentController {

    @Autowired
    private RestaurentInfoService service;

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(new ApiResponse<>(200, "Test API is working", "Test Work"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurentInfo>> create(@RequestBody RestaurentInfo info) {
        RestaurentInfo saved = service.create(info);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant created successfully", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurentInfo>>> getAll() {
        List<RestaurentInfo> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "All restaurants fetched successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurentInfo>> getById(@PathVariable UUID id) {
        Optional<RestaurentInfo> info = service.getById(id);
        return info.map(restaurentInfo ->
                        ResponseEntity.ok(new ApiResponse<>(200, "Restaurant found", restaurentInfo)))
                .orElse(ResponseEntity.status(404)
                        .body(new ApiResponse<>(404, "Restaurant not found", null)));
    }

    @GetMapping("/getInfo/{restaurantCode}")
    public ResponseEntity<?> getRestaurantInfoByCode(@PathVariable String restaurantCode) {
        Optional<RestaurentInfo> info = service.getByrestaurantCode(restaurantCode);
        return ResponseEntity.ok(info.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurentInfo>> update(@PathVariable UUID id, @RequestBody RestaurentInfo updated) {
        RestaurentInfo info = service.update(id, updated);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant updated successfully", info));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant deleted successfully", "Deleted ID: " + id));
    }
}
