package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.IngredientDTO;

import java.util.Optional;

public interface IngredientService {
    IngredientDTO save(IngredientDTO dto);
    Optional<IngredientDTO> findOne(Integer id);
    void delete(Integer id);
}
