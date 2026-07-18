package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.MenuItemCustomization;
import com.example.ceffeshop_mobileappdev.entity.MenuItemCustomizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface MenuItemCustomizationRepository extends JpaRepository<MenuItemCustomization, MenuItemCustomizationId>, JpaSpecificationExecutor<MenuItemCustomization> {

    @Query("SELECT mc.groupID FROM MenuItemCustomization mc WHERE mc.menuItemID.id = :menuItemId")
    List<CustomizationGroup> findGroupsByMenuItemId(@Param("menuItemId") Integer menuItemId);
}
