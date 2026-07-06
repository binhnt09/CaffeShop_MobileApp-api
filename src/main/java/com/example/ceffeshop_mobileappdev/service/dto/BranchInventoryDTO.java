package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class BranchInventoryDTO {
    private Integer branchId;
    private Integer ingredientId;
    private String ingredientName;
    private BigDecimal quantityAvailable;
    private Instant lastUpdated;
}
