package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.RecipeService;
import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import com.example.ceffeshop_mobileappdev.entity.Recipe;
import com.example.ceffeshop_mobileappdev.entity.RecipeId;
import com.example.ceffeshop_mobileappdev.repository.RecipeRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public RecipeDTO save(RecipeDTO dto) {
        Recipe entity = recipeMapper.toEntity(dto);
        entity = recipeRepository.save(entity);
        return recipeMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RecipeDTO> findOne(RecipeId id) {
        return recipeRepository.findById(id).map(recipeMapper::toDto);
    }

    @Override
    public void delete(RecipeId id) {
        recipeRepository.deleteById(id);
    }
}
