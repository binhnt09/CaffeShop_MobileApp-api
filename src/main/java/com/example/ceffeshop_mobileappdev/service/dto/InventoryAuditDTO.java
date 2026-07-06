package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class InventoryAuditDTO {
    private Integer id;
    private Integer branchId;
    private Integer managerId;
    private Instant auditDate;
    private String status;
    private String remarks;
}
