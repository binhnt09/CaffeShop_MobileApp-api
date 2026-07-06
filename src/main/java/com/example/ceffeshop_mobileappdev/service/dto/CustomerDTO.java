package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class CustomerDTO {
    private Integer id;
    private Integer userId;
    private String userFullName;
    private Integer loyaltyPoints;
    private String tier;
    private Instant createdAt;
}
