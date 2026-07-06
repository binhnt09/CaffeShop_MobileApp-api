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
public class BranchMenuItemId implements Serializable {
    private static final long serialVersionUID = 5178663958155814996L;
    @NotNull
    @Column(name = "BranchID", nullable = false)
    private Integer branchID;

    @NotNull
    @Column(name = "MenuItemID", nullable = false)
    private Integer menuItemID;


}