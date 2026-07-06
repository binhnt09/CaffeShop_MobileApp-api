package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.CouponDTO;

import java.util.Optional;

public interface CouponService {
    CouponDTO save(CouponDTO dto);
    Optional<CouponDTO> findOne(Integer id);
    void delete(Integer id);
}
