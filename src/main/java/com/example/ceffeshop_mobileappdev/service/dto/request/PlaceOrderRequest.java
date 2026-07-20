package com.example.ceffeshop_mobileappdev.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class PlaceOrderRequest {
    @NotNull private Integer branchId;
    @NotNull private String fulfillmentMode; // "DineIn" | "Takeaway"
    @NotNull private String paymentMethod;   // "Cash" | "MoMo" | "VNPAY"
    private String couponCode;               // nullable
    private Integer redeemPoints;            // nullable

    @NotNull @NotEmpty
    private List<OrderItemRequest> items;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getFulfillmentMode() {
        return fulfillmentMode;
    }

    public void setFulfillmentMode(String fulfillmentMode) {
        this.fulfillmentMode = fulfillmentMode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getRedeemPoints() {
        return redeemPoints;
    }

    public void setRedeemPoints(Integer redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    @Data
    public static class OrderItemRequest {
        @NotNull private Integer menuItemId;
        @NotNull private Integer quantity;
        private List<Integer> customizationOptionIds; // nullable
        private String notes;

        public Integer getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(Integer menuItemId) {
            this.menuItemId = menuItemId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public List<Integer> getCustomizationOptionIds() {
            return customizationOptionIds;
        }

        public void setCustomizationOptionIds(List<Integer> customizationOptionIds) {
            this.customizationOptionIds = customizationOptionIds;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }
}
