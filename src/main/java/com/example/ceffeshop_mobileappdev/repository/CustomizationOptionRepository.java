package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.CustomizationOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationOptionRepository extends JpaRepository<CustomizationOption, Integer>, JpaSpecificationExecutor<CustomizationOption> {
}
