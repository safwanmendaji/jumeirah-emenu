package com.jumeirah.serviceimpl;

import com.jumeirah.model.RestaurentInfo;
import com.jumeirah.repository.RestaurentInfoRepository;
import com.jumeirah.service.RestaurentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurentInfoServiceImpl implements RestaurentInfoService {

    @Autowired
    private RestaurentInfoRepository repository;

    @Override
    public RestaurentInfo create(RestaurentInfo info) {
        // Optional: check for duplicate restaurantCode manually (MongoDB will also enforce unique index)
        if (repository.existsByRestaurantCode(info.getRestaurantCode())) {
            throw new DuplicateKeyException("Restaurant code already exists: " + info.getRestaurantCode());
        }
        return repository.save(info);
    }

    @Override
    public List<RestaurentInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RestaurentInfo> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RestaurentInfo> getByrestaurantCode(String restaurantCode) {
        return repository.findByRestaurantCode(restaurantCode);
    }

    @Override
    public RestaurentInfo update(UUID id, RestaurentInfo updatedInfo) {
        Optional<RestaurentInfo> existingOpt = repository.findById(id);
        if (existingOpt.isPresent()) {
            updatedInfo.setRestaurentInfoId(id); // Ensure the ID stays the same
            return repository.save(updatedInfo);
        } else {
            throw new RuntimeException("Restaurant not found with ID: " + id);
        }
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Restaurant not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
