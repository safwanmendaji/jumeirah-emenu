package com.jumeirah.controllers;


import com.jumeirah.model.ApiResponse;
import com.jumeirah.model.RestaurentInfo;
import com.jumeirah.service.RestaurentInfoService;
import com.jumeirah.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurentController {

    @Autowired
    private RestaurentInfoService service;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<RestaurentInfo>> createRestaurant(
            @RequestPart("info") RestaurentInfo info,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "logo", required = false) MultipartFile logo
    ) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageUrl = fileUploadUtil.saveFile(image);
            info.setImageUrl(imageUrl);
        }

        if (logo != null && !logo.isEmpty()) {
            String logoUrl = fileUploadUtil.saveFile(logo);
            info.setLogoUrl(logoUrl);
        }

        RestaurentInfo saved = service.create(info);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant created successfully", saved));
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(new ApiResponse<>(200, "Test API is working", "Test Work"));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RestaurentInfo>> create(@RequestBody RestaurentInfo info) {
        RestaurentInfo saved = service.create(info);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant created successfully", saved));
    }

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<RestaurentInfo>>> getAll() {
        List<RestaurentInfo> list = service.getAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "All restaurants fetched successfully", list));
    }

    @GetMapping("/getById/{id}")
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

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<RestaurentInfo>> update(@PathVariable UUID id, @RequestBody RestaurentInfo updated) {
//        RestaurentInfo info = service.update(id, updated);
//        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant updated successfully", info));
//    }


    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<RestaurentInfo>> updateWithFiles(
            @PathVariable UUID id,
            @RequestPart("info") RestaurentInfo updatedInfo,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "logo", required = false) MultipartFile logo
    ) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageUrl = fileUploadUtil.saveFile(image);
            updatedInfo.setImageUrl(imageUrl);
        }

        if (logo != null && !logo.isEmpty()) {
            String logoUrl = fileUploadUtil.saveFile(logo);
            updatedInfo.setLogoUrl(logoUrl);
        }

        RestaurentInfo updated = service.update(id, updatedInfo);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant updated successfully with files", updated));
    }



    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant deleted successfully", "Deleted ID: " + id));
    }

    @DeleteMapping("/'deleteByCode'/{restaurantCode}")
    public ResponseEntity<ApiResponse<String>> deleteByCode(@PathVariable String restaurantCode) {
        Optional<RestaurentInfo> info = service.getByrestaurantCode(restaurantCode);

        if (info.isPresent()) {
            service.delete(info.get().getRestaurentInfoId());
            return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant deleted successfully", "Deleted by code: " + restaurantCode));
        }

        return ResponseEntity.status(404)
                .body(new ApiResponse<>(404, "Restaurant not found with code: " + restaurantCode, null));
    }

}
