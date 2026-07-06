package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationGroupRepository extends JpaRepository<CustomizationGroup, Integer>, JpaSpecificationExecutor<CustomizationGroup> {
}
