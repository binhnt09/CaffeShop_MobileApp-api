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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CustomizationGroupDetailDTO> getCustomizationGroups() {
        return customizationGroups;
    }

    public void setCustomizationGroups(List<CustomizationGroupDetailDTO> customizationGroups) {
        this.customizationGroups = customizationGroups;
    }

    @Data
    public static class CustomizationGroupDetailDTO {
        private Integer groupId;
        private String groupName;
        private Integer minSelect;
        private Integer maxSelect;
        private List<CustomizationOptionDetailDTO> options;

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Integer getMinSelect() {
            return minSelect;
        }

        public void setMinSelect(Integer minSelect) {
            this.minSelect = minSelect;
        }

        public Integer getMaxSelect() {
            return maxSelect;
        }

        public void setMaxSelect(Integer maxSelect) {
            this.maxSelect = maxSelect;
        }

        public List<CustomizationOptionDetailDTO> getOptions() {
            return options;
        }

        public void setOptions(List<CustomizationOptionDetailDTO> options) {
            this.options = options;
        }
    }

    @Data
    public static class CustomizationOptionDetailDTO {
        private Integer optionId;
        private String optionName;
        private BigDecimal extraPrice;
        private Boolean isAvailable;

        public Integer getOptionId() {
            return optionId;
        }

        public void setOptionId(Integer optionId) {
            this.optionId = optionId;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public BigDecimal getExtraPrice() {
            return extraPrice;
        }

        public void setExtraPrice(BigDecimal extraPrice) {
            this.extraPrice = extraPrice;
        }

        public Boolean getIsAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(Boolean isAvailable) {
            this.isAvailable = isAvailable;
        }
    }
}
