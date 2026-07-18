package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>, JpaSpecificationExecutor<Coupon> {
    Optional<Coupon> findByCode(String code);
    Optional<Coupon> findByCodeAndIsActiveTrue(String code);
}
