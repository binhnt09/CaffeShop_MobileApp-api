package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.UserBranch;
import com.example.ceffeshop_mobileappdev.entity.UserBranchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBranchRepository extends JpaRepository<UserBranch, UserBranchId>, JpaSpecificationExecutor<UserBranch> {
}
