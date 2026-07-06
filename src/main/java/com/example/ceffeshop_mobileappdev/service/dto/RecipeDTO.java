package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RecipeDTO {
    private Integer menuItemId;
    private Integer ingredientId;
    private String ingredientName;
    private BigDecimal quantityRequired;
}
