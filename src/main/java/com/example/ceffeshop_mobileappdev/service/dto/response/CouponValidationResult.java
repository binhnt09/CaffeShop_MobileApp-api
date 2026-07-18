package com.example.ceffeshop_mobileappdev.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponValidationResult {
    private boolean valid;
    private BigDecimal discountAmount;
    private String couponCode;
    private String message;
}
