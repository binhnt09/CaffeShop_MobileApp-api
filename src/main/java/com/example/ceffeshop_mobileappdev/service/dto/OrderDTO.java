package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class OrderDTO {
    private Integer id;
    private Integer customerId;
    private String customerName;
    private Integer branchId;
    private Integer cashierId;
    private Integer shiftId;
    private String orderType;
    private String status;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private Instant createdAt;
}
