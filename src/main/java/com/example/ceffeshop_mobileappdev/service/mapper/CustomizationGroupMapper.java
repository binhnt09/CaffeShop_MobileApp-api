package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomizationGroupMapper {
    CustomizationGroupDTO toDto(CustomizationGroup entity);
    CustomizationGroup toEntity(CustomizationGroupDTO dto);
}
