package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import com.example.ceffeshop_mobileappdev.entity.RecipeId;
import java.util.Optional;

public interface RecipeService {
    RecipeDTO save(RecipeDTO dto);
    Optional<RecipeDTO> findOne(RecipeId id);
    void delete(RecipeId id);
}
