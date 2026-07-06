package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "InventoryAuditDetails")
public class InventoryAuditDetail {
    @EmbeddedId
    private InventoryAuditDetailId id;

    @MapsId("auditID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AuditID", nullable = false)
    private InventoryAudit auditID;

    @MapsId("ingredientID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IngredientID", nullable = false)
    private Ingredient ingredientID;

    @NotNull
    @Column(name = "SystemStock", nullable = false, precision = 10, scale = 2)
    private BigDecimal systemStock;

    @NotNull
    @Column(name = "ActualStock", nullable = false, precision = 10, scale = 2)
    private BigDecimal actualStock;

    @NotNull
    @Column(name = "Variance", nullable = false, precision = 10, scale = 2)
    private BigDecimal variance;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ReasonCode", length = 50)
    private String reasonCode;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Notes")
    private String notes;


}