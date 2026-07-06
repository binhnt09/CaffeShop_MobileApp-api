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
public class BranchInventoryId implements Serializable {
    private static final long serialVersionUID = -6830706612105102332L;
    @NotNull
    @Column(name = "BranchID", nullable = false)
    private Integer branchID;

    @NotNull
    @Column(name = "IngredientID", nullable = false)
    private Integer ingredientID;


}