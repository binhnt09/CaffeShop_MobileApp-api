package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ShiftDTO {
    private Integer id;
    private Integer branchId;
    private Integer cashierId;
    private Instant startTime;
    private Instant endTime;
    private BigDecimal startingCash;
    private BigDecimal endingCash;
    private BigDecimal totalSales;
    private String status;
}
