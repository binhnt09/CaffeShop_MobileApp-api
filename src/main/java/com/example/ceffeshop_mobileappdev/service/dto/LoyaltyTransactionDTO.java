package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class LoyaltyTransactionDTO {
    private Integer id;
    private Integer orderId;
    private Integer pointsEarned;
    private Integer pointsRedeemed;
    private String transactionType;
    private Instant createdAt;
}
