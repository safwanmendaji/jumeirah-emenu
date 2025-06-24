package com.jumeirah.service;

import com.jumeirah.model.RestaurentInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurentInfoService {
    RestaurentInfo create(RestaurentInfo info);
    List<RestaurentInfo> getAll();
    Optional<RestaurentInfo> getById(UUID id);

    Optional<RestaurentInfo> getByrestaurantCode(String restaurantCode);
    RestaurentInfo update(UUID id, RestaurentInfo updated);
    void delete(UUID id);
}
