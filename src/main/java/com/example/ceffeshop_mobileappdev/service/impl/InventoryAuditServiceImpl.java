package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.InventoryAuditService;
import com.example.ceffeshop_mobileappdev.service.dto.InventoryAuditDTO;
import com.example.ceffeshop_mobileappdev.entity.InventoryAudit;

import com.example.ceffeshop_mobileappdev.repository.InventoryAuditRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.InventoryAuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryAuditServiceImpl implements InventoryAuditService {

    private final InventoryAuditRepository inventoryAuditRepository;
    private final InventoryAuditMapper inventoryAuditMapper;

    @Override
    public InventoryAuditDTO save(InventoryAuditDTO dto) {
        InventoryAudit entity = inventoryAuditMapper.toEntity(dto);
        entity = inventoryAuditRepository.save(entity);
        return inventoryAuditMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InventoryAuditDTO> findOne(Integer id) {
        return inventoryAuditRepository.findById(id).map(inventoryAuditMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        inventoryAuditRepository.deleteById(id);
    }
}
