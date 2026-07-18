package com.example.ceffeshop_mobileappdev.service.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PlaceOrderResponse {
    private Integer orderId;
    private String orderCode;
    private String orderStatus;
    private String paymentStatus;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private Integer pointsEarned;
    private Instant createdAt;
}
