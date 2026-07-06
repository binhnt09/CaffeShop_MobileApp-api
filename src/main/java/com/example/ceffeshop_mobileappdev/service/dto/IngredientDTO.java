package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;

@Data
public class IngredientDTO {
    private Integer id;
    private String name;
    private String unit;
    private Integer reorderLevel;
}
