package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Category;
import com.example.ceffeshop_mobileappdev.service.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "categoryName", target = "name")
    CategoryDTO toDto(Category entity);

    @Mapping(source = "name", target = "categoryName")
    @Mapping(target = "status", constant = "Active")
    Category toEntity(CategoryDTO dto);
}
