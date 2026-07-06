package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MenuItemCustomizations")
public class MenuItemCustomization {
    @EmbeddedId
    private MenuItemCustomizationId id;

    @MapsId("menuItemID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MenuItemID", nullable = false)
    private MenuItem menuItemID;

    @MapsId("groupID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GroupID", nullable = false)
    private CustomizationGroup groupID;


}