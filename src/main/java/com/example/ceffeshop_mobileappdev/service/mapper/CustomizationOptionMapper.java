package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.CustomizationOption;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomizationOptionMapper {
    CustomizationOptionDTO toDto(CustomizationOption entity);
    CustomizationOption toEntity(CustomizationOptionDTO dto);
}
