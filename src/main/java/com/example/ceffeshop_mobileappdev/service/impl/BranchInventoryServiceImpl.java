package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.BranchInventoryService;
import com.example.ceffeshop_mobileappdev.service.dto.BranchInventoryDTO;
import com.example.ceffeshop_mobileappdev.entity.BranchInventory;
import com.example.ceffeshop_mobileappdev.entity.BranchInventoryId;
import com.example.ceffeshop_mobileappdev.repository.BranchInventoryRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchInventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchInventoryServiceImpl implements BranchInventoryService {

    private final BranchInventoryRepository branchInventoryRepository;
    private final BranchInventoryMapper branchInventoryMapper;

    @Override
    public BranchInventoryDTO save(BranchInventoryDTO dto) {
        BranchInventory entity = branchInventoryMapper.toEntity(dto);
        entity = branchInventoryRepository.save(entity);
        return branchInventoryMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchInventoryDTO> findOne(BranchInventoryId id) {
        return branchInventoryRepository.findById(id).map(branchInventoryMapper::toDto);
    }

    @Override
    public void delete(BranchInventoryId id) {
        branchInventoryRepository.deleteById(id);
    }
}
