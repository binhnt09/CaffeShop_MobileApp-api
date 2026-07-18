package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;

@Data
public class LoyaltyInfoDTO {
    private Integer customerId;
    private Integer loyaltyPoints;
    private String membershipTier;
    private Integer pointsToNextTier;
    private String nextTierName;
    private double tierProgress;
}
