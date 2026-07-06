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
public class RecipeId implements Serializable {
    private static final long serialVersionUID = 9079104497015282084L;
    @NotNull
    @Column(name = "MenuItemID", nullable = false)
    private Integer menuItemID;

    @NotNull
    @Column(name = "IngredientID", nullable = false)
    private Integer ingredientID;


}