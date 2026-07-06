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
public class MenuItemCustomizationId implements Serializable {
    private static final long serialVersionUID = -3268465171785281336L;
    @NotNull
    @Column(name = "MenuItemID", nullable = false)
    private Integer menuItemID;

    @NotNull
    @Column(name = "GroupID", nullable = false)
    private Integer groupID;


}