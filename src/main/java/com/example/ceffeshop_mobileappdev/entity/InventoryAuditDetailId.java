package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class InventoryAuditDetailId implements Serializable {
    private static final long serialVersionUID = -2246299960449027919L;
    @NotNull
    @Column(name = "AuditID", nullable = false)
    private Integer auditID;

    @NotNull
    @Column(name = "IngredientID", nullable = false)
    private Integer ingredientID;


}