package com.jumeirah.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @GetMapping("/test")
    public String test(){
        return "Test Work";
    }

//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/place-order")
//    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
//        return orderService.placeOrder(order);
//    }
}
