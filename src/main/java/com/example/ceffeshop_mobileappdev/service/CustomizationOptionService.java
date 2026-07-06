package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;

import java.util.Optional;

public interface CustomizationOptionService {
    CustomizationOptionDTO save(CustomizationOptionDTO dto);
    Optional<CustomizationOptionDTO> findOne(Integer id);
    void delete(Integer id);
}
