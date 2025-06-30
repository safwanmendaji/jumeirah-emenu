package com.jumeirah.service;

import com.jumeirah.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> placeOrder(OrderDTO orderDTO);
}