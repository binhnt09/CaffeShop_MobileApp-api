package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Recipe;
import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeDTO toDto(Recipe entity);
    Recipe toEntity(RecipeDTO dto);
}
