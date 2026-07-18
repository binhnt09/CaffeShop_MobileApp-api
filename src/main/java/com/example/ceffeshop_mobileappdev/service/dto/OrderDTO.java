package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class OrderDTO {
    private Integer id;
    private String orderCode;
    private Integer customerId;
    private String customerName;
    private Integer branchId;
    private Integer cashierId;
    private Integer shiftId;
    private String orderSource;
    private String fulfillmentMode;
    private BigDecimal totalAmount;
    private BigDecimal vATAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String status;
    private String paymentStatus;
    private String paymentMethod;
    private Instant createdAt;
    private Instant updatedAt;
}
