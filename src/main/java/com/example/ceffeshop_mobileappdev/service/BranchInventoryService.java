package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.BranchInventoryDTO;
import com.example.ceffeshop_mobileappdev.entity.BranchInventoryId;
import java.util.Optional;

public interface BranchInventoryService {
    BranchInventoryDTO save(BranchInventoryDTO dto);
    Optional<BranchInventoryDTO> findOne(BranchInventoryId id);
    void delete(BranchInventoryId id);
}
