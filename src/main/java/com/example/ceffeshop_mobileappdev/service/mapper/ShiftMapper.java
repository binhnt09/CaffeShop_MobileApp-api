package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Shift;
import com.example.ceffeshop_mobileappdev.service.dto.ShiftDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    ShiftDTO toDto(Shift entity);
    Shift toEntity(ShiftDTO dto);
}
