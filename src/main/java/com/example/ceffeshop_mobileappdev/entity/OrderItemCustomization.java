package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "OrderItemCustomizations")
public class OrderItemCustomization {
    @EmbeddedId
    private OrderItemCustomizationId id;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "OptionPrice", nullable = false, precision = 18, scale = 2)
    private BigDecimal optionPrice;


}