package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.InventoryAudit;
import com.example.ceffeshop_mobileappdev.service.dto.InventoryAuditDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryAuditMapper {
    InventoryAuditDTO toDto(InventoryAudit entity);
    InventoryAudit toEntity(InventoryAuditDTO dto);
}
