package com.jumeirah.service;

import org.springframework.http.ResponseEntity;

public interface QRService {
    ResponseEntity getRestaurantInfoAndMenu(String restaurantCode, String tableNumber);
}
