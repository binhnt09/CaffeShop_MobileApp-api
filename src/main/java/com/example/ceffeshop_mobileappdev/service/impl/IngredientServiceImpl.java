package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.IngredientService;
import com.example.ceffeshop_mobileappdev.service.dto.IngredientDTO;
import com.example.ceffeshop_mobileappdev.entity.Ingredient;

import com.example.ceffeshop_mobileappdev.repository.IngredientRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public IngredientDTO save(IngredientDTO dto) {
        Ingredient entity = ingredientMapper.toEntity(dto);
        entity = ingredientRepository.save(entity);
        return ingredientMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IngredientDTO> findOne(Integer id) {
        return ingredientRepository.findById(id).map(ingredientMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        ingredientRepository.deleteById(id);
    }
}
