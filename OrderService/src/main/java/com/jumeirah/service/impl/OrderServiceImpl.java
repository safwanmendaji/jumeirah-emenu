package com.jumeirah.service.impl;

import com.jumeirah.dto.CustomizationDTO;
import com.jumeirah.dto.OrderDTO;
import com.jumeirah.dto.OrderItemDTO;
import com.jumeirah.model.*;
import com.jumeirah.repository.OrderItemsRepository;
import com.jumeirah.repository.OrderRepository;
import com.jumeirah.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public ResponseEntity<?> placeOrder(OrderDTO orderDTO) {
        UUID restaurantUUID = UUID.fromString(orderDTO.getRestaurantId());

        // Check for existing open order
        Optional<Order> existingOrderOpt = orderRepository
                .findByRestaurantIdAndTableNumberAndIsOrderCloseFalse(restaurantUUID, orderDTO.getTableNo());

        Order order = existingOrderOpt.orElseGet(() -> {
            Order newOrder = new Order();
            newOrder.setRestaurantId(restaurantUUID);
            newOrder.setTableNumber(orderDTO.getTableNo());
            newOrder.setDateTime(LocalDateTime.now());
            newOrder.setOrderClose(false);
            return orderRepository.save(newOrder);
        });

        // Add order items
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setItemId(UUID.fromString(itemDTO.getItemId()));
            orderItem.setItemPrice(itemDTO.getItemPrice());
            orderItem.setItemStatus(ItemStatus.PREPARING);
            orderItem.setCustomizationId(itemDTO.getCustomizationId());
            if (itemDTO.getCustomizationOption() != null) {
                List<OrderItemCustomization> customizationList = new ArrayList<>();
                for (CustomizationDTO custDTO : itemDTO.getCustomizationOption()) {
                    OrderItemCustomization customization = new OrderItemCustomization();
                    customization.setCustomizationId(UUID.fromString(custDTO.getCustomizationOptionId()));
                    customization.setPrice(custDTO.getCustomizationOptionPrice());
                    customizationList.add(customization);
                }
                orderItem.setCustomizationsOption(customizationList);
            }

            orderItemsRepository.save(orderItem);
        }

        return ResponseEntity.ok( new ApiResponse<>(200, "Order placed successfully with ID: " + order.getOrderId() , null));
    }
}
