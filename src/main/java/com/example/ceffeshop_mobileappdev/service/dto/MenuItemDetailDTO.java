package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuItemDetailDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private String imageUrl;
    private String status;
    private Integer categoryId;
    private String categoryName;
    private List<CustomizationGroupDetailDTO> customizationGroups;

    @Data
    public static class CustomizationGroupDetailDTO {
        private Integer groupId;
        private String groupName;
        private Integer minSelect;
        private Integer maxSelect;
        private List<CustomizationOptionDetailDTO> options;
    }

    @Data
    public static class CustomizationOptionDetailDTO {
        private Integer optionId;
        private String optionName;
        private BigDecimal extraPrice;
        private Boolean isAvailable;
    }
}
