package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.InventoryAuditDTO;

import java.util.Optional;

public interface InventoryAuditService {
    InventoryAuditDTO save(InventoryAuditDTO dto);
    Optional<InventoryAuditDTO> findOne(Integer id);
    void delete(Integer id);
}
