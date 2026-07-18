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

    @Data
    public static class OrderItemRequest {
        @NotNull private Integer menuItemId;
        @NotNull private Integer quantity;
        private List<Integer> customizationOptionIds; // nullable
        private String notes;
    }
}
