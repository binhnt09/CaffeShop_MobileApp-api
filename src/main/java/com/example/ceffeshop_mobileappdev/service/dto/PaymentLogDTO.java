package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PaymentLogDTO {
    private Integer id;
    private Integer orderId;
    private String paymentMethod;
    private BigDecimal amount;
    private String status;
    private String transactionId;
    private Instant createdAt;
}
