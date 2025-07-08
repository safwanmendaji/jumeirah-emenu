package com.jumeirah.controllers;


import com.jumeirah.dto.OrderDTO;
import com.jumeirah.service.OrderService;
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

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    @CrossOrigin
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(orderDTO);
    }
}
