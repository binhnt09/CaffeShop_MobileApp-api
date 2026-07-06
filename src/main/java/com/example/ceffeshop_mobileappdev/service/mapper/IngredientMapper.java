package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Ingredient;
import com.example.ceffeshop_mobileappdev.service.dto.IngredientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientDTO toDto(Ingredient entity);
    Ingredient toEntity(IngredientDTO dto);
}
