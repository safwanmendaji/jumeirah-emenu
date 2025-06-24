package com.jumeirah.service.impl;

import com.jumeirah.model.ApiResponse;
import com.jumeirah.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QRServiceImpl implements QRService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ResponseEntity<?> getRestaurantInfoAndMenu(String restaurantCode, String tableNumber) {
        //get The RestaurantInfo
        String restauranturl = "http://localhost:9091/api/restaurant/getInfo/" + restaurantCode;
        ResponseEntity<Map> response = restTemplate.getForEntity(restauranturl, Map.class);
        Map<String, Object> restaurantInfo = response.getBody();

        System.out.println("Restaurant Info Map: " + restaurantInfo);

        String menuUrl = "http://localhost:9091/api/menu/byrestaurantid/" + restaurantInfo.get("restaurentInfoId");
        ResponseEntity<List> menuResponse = restTemplate.getForEntity(menuUrl, List.class);
        List<Map<String, Object>> menuList = menuResponse.getBody();

        System.out.println("Menu List: " + menuList);

        System.out.println("Restaurant Info Map: " + restaurantInfo);


        Map<String , Object> finalResponse = new HashMap<>();
        finalResponse.put("restaurentInfo" , restaurantInfo);
        finalResponse.put("restaurentMenu" , menuList);

        return ResponseEntity.ok(new ApiResponse<>(200, "Restaurant details get successfully", finalResponse));
    }
}
