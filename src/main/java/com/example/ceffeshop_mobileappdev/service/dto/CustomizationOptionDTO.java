package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CustomizationOptionDTO {
    private Integer id;
    private Integer groupId;
    private String optionName;
    private BigDecimal extraPrice;
    private Boolean isAvailable;
}
