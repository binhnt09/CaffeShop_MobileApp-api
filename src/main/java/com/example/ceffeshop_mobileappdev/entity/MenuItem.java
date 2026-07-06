package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "MenuItems")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuItemID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category categoryID;

    @Size(max = 20)
    @NotNull
    @Column(name = "SKU", nullable = false, length = 20)
    private String sku;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "ItemName", nullable = false, length = 100)
    private String itemName;

    @NotNull
    @Column(name = "BasePrice", nullable = false, precision = 18, scale = 2)
    private BigDecimal basePrice;

    @NotNull
    @ColumnDefault("10.00")
    @Column(name = "TaxRate", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;

    @ColumnDefault("1")
    @Column(name = "IsAvailable")
    private Boolean isAvailable;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;


}