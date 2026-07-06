package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;

import java.util.Optional;

public interface CustomizationGroupService {
    CustomizationGroupDTO save(CustomizationGroupDTO dto);
    Optional<CustomizationGroupDTO> findOne(Integer id);
    void delete(Integer id);
}
