package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.BranchMenuItem;
import com.example.ceffeshop_mobileappdev.entity.BranchMenuItemId;
import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BranchMenuItemRepository extends JpaRepository<BranchMenuItem, BranchMenuItemId>, JpaSpecificationExecutor<BranchMenuItem> {

    @Query("SELECT bm.menuItemID FROM BranchMenuItem bm WHERE bm.branchID.id = :branchId AND bm.isAvailable = true")
    List<MenuItem> findAvailableMenuItemsByBranchId(@Param("branchId") Integer branchId);
}
