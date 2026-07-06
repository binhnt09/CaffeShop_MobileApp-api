package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CouponDTO {
    private Integer id;
    private String code;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minOrderAmount;
    private BigDecimal maxDiscountAmount;
    private Instant startDate;
    private Instant endDate;
    private Integer usageLimit;
    private Integer usedCount;
    private String status;
}
