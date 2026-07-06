package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "BranchMenuItems")
public class BranchMenuItem {
    @EmbeddedId
    private BranchMenuItemId id;

    @MapsId("branchID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @MapsId("menuItemID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MenuItemID", nullable = false)
    private MenuItem menuItemID;

    @ColumnDefault("1")
    @Column(name = "IsAvailable")
    private Boolean isAvailable;


}