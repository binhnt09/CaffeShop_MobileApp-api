package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;

@Data
public class CustomizationGroupDTO {
    private Integer id;
    private String groupName;
    private String description;
    private Boolean isRequired;
    private Integer maxOptions;
}
