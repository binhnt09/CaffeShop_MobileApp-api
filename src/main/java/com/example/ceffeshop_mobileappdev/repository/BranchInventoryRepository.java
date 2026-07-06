package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.BranchInventory;
import com.example.ceffeshop_mobileappdev.entity.BranchInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchInventoryRepository extends JpaRepository<BranchInventory, BranchInventoryId>, JpaSpecificationExecutor<BranchInventory> {
}
