package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "BranchInventory")
public class BranchInventory {
    @EmbeddedId
    private BranchInventoryId id;

    @MapsId("branchID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @MapsId("ingredientID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IngredientID", nullable = false)
    private Ingredient ingredientID;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "CurrentStock", nullable = false, precision = 10, scale = 2)
    private BigDecimal currentStock;

    @NotNull
    @ColumnDefault("500.00")
    @Column(name = "SafeThreshold", nullable = false, precision = 10, scale = 2)
    private BigDecimal safeThreshold;

    @ColumnDefault("getdate()")
    @Column(name = "UpdatedAt")
    private Instant updatedAt;


}