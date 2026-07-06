package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class MenuItemDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private Integer categoryId;
    private String categoryName;
    private String imageUrl;
    private String status;
    private Instant createdAt;
}
