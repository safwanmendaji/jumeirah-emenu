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

//    @Override
//    public RestaurentInfo update(UUID id, RestaurentInfo updatedInfo) {
//        Optional<RestaurentInfo> existingOpt = repository.findById(id);
//        if (existingOpt.isPresent()) {
//            updatedInfo.setRestaurentInfoId(id); // Ensure the ID stays the same
//            return repository.save(updatedInfo);
//        } else {
//            throw new RuntimeException("Restaurant not found with ID: " + id);
//        }
//    }

    @Override
    public RestaurentInfo update(UUID id, RestaurentInfo updatedInfo) {
        Optional<RestaurentInfo> existingOpt = repository.findById(id);
        if (existingOpt.isPresent()) {
            RestaurentInfo existing = existingOpt.get();

            if (updatedInfo.getCurrencyCode() != null) existing.setCurrencyCode(updatedInfo.getCurrencyCode());
            if (updatedInfo.getCurrencySymbol() != null) existing.setCurrencySymbol(updatedInfo.getCurrencySymbol());
            if (updatedInfo.getRestaurantName() != null) existing.setRestaurantName(updatedInfo.getRestaurantName());
            if (updatedInfo.getRestaurantCode() != null) existing.setRestaurantCode(updatedInfo.getRestaurantCode());
            if (updatedInfo.getImageUrl() != null) existing.setImageUrl(updatedInfo.getImageUrl());
            if (updatedInfo.getRestaurantLocation() != null) existing.setRestaurantLocation(updatedInfo.getRestaurantLocation());
            if (updatedInfo.getLogoUrl() != null) existing.setLogoUrl(updatedInfo.getLogoUrl());

            if (updatedInfo.getInRoomDining() != null) existing.setInRoomDining(updatedInfo.getInRoomDining());
            if (updatedInfo.getOpenForGuestOrder() != null) existing.setOpenForGuestOrder(updatedInfo.getOpenForGuestOrder());

            if (updatedInfo.getHelpTextRoomNumber() != null) existing.setHelpTextRoomNumber(updatedInfo.getHelpTextRoomNumber());
            if (updatedInfo.getHelpTextLastName() != null) existing.setHelpTextLastName(updatedInfo.getHelpTextLastName());

            if (updatedInfo.getDeliveryTime() != null) existing.setDeliveryTime(updatedInfo.getDeliveryTime());

            if (updatedInfo.getSupportedLanguages() != null) existing.setSupportedLanguages(updatedInfo.getSupportedLanguages());
            if (updatedInfo.getMenuLanguage() != null) existing.setMenuLanguage(updatedInfo.getMenuLanguage());

            if (updatedInfo.getDisclaimer() != null) existing.setDisclaimer(updatedInfo.getDisclaimer());

            if (updatedInfo.getPaymentOnlineEnabled() != null) existing.setPaymentOnlineEnabled(updatedInfo.getPaymentOnlineEnabled());
            if (updatedInfo.getPaymentToRoomEnabled() != null) existing.setPaymentToRoomEnabled(updatedInfo.getPaymentToRoomEnabled());
            if (updatedInfo.getIsSplitPayment() != null) existing.setIsSplitPayment(updatedInfo.getIsSplitPayment());
            if (updatedInfo.getOnlyPayment() != null) existing.setOnlyPayment(updatedInfo.getOnlyPayment());

            if (updatedInfo.getItemUnavailable() != null) existing.setItemUnavailable(updatedInfo.getItemUnavailable());

            if (updatedInfo.getSimphonyVersion() != null) existing.setSimphonyVersion(updatedInfo.getSimphonyVersion());
            if (updatedInfo.getTheme() != null) existing.setTheme(updatedInfo.getTheme());

            if (updatedInfo.getBuzzer() != null) existing.setBuzzer(updatedInfo.getBuzzer());
            if (updatedInfo.getIsPickup() != null) existing.setIsPickup(updatedInfo.getIsPickup());

            if (updatedInfo.getIrb() != null) existing.setIrb(updatedInfo.getIrb());
            if (updatedInfo.getSports() != null) existing.setSports(updatedInfo.getSports());
            if (updatedInfo.getParentOutlet() != null) existing.setParentOutlet(updatedInfo.getParentOutlet());
            if (updatedInfo.getIrbName() != null) existing.setIrbName(updatedInfo.getIrbName());
            if (updatedInfo.getIsIrb() != null) existing.setIsIrb(updatedInfo.getIsIrb());

            return repository.save(existing);
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
