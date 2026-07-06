package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.BranchMenuItem;
import com.example.ceffeshop_mobileappdev.entity.BranchMenuItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchMenuItemRepository extends JpaRepository<BranchMenuItem, BranchMenuItemId>, JpaSpecificationExecutor<BranchMenuItem> {
}
