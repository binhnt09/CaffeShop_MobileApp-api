package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemDTO toDto(MenuItem entity);
    MenuItem toEntity(MenuItemDTO dto);
}
