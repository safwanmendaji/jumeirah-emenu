package com.jumeirah.controllers;


import com.jumeirah.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qr")
public class QRController {

    @Autowired
    private QRService qrService;

    @GetMapping("/test")
    public String test(){
        return "Test Work";
    }


    @GetMapping("/restaurant-menus")
    @CrossOrigin
    public ResponseEntity<?> getRestaurantInfo(
            @RequestParam String restaurantCode,
            @RequestParam String tableNumber) {

        return qrService.getRestaurantInfoAndMenu(restaurantCode, tableNumber);
    }

}
