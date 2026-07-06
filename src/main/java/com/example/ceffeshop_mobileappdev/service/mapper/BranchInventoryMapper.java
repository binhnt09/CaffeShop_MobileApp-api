package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.BranchInventory;
import com.example.ceffeshop_mobileappdev.service.dto.BranchInventoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchInventoryMapper {
    BranchInventoryDTO toDto(BranchInventory entity);
    BranchInventory toEntity(BranchInventoryDTO dto);
}
