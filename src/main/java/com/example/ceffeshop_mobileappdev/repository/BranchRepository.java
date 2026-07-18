package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>, JpaSpecificationExecutor<Branch> {
    List<Branch> findByStatus(String status);
}
