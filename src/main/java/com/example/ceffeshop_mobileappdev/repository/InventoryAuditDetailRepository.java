package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.InventoryAuditDetail;
import com.example.ceffeshop_mobileappdev.entity.InventoryAuditDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryAuditDetailRepository extends JpaRepository<InventoryAuditDetail, InventoryAuditDetailId>, JpaSpecificationExecutor<InventoryAuditDetail> {
}
