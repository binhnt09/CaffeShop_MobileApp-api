package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "categoryID.id", target = "categoryId")
    @Mapping(source = "categoryID.categoryName", target = "categoryName")
    @Mapping(source = "isAvailable", target = "status", qualifiedByName = "booleanToStatus")
    MenuItemDTO toDto(MenuItem entity);

    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "categoryId", target = "categoryID.id")
    @Mapping(source = "status", target = "isAvailable", qualifiedByName = "statusToBoolean")
    @Mapping(target = "taxRate", constant = "10.00")
    @Mapping(target = "sku", constant = "SKU-TEMP")
    MenuItem toEntity(MenuItemDTO dto);

    @Named("booleanToStatus")
    default String booleanToStatus(Boolean isAvailable) {
        if (isAvailable == null) return "Available";
        return isAvailable ? "Available" : "Unavailable";
    }

    @Named("statusToBoolean")
    default Boolean statusToBoolean(String status) {
        if (status == null) return true;
        return "Available".equalsIgnoreCase(status) || "Active".equalsIgnoreCase(status);
    }
}
