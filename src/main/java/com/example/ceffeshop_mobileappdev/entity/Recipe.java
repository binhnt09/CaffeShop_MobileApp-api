package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Recipes")
public class Recipe {
    @EmbeddedId
    private RecipeId id;

    @MapsId("ingredientID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IngredientID", nullable = false)
    private Ingredient ingredientID;

    @NotNull
    @Column(name = "StandardQuantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal standardQuantity;


}