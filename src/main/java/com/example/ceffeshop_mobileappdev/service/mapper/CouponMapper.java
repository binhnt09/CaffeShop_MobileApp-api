package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Coupon;
import com.example.ceffeshop_mobileappdev.service.dto.CouponDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponDTO toDto(Coupon entity);
    Coupon toEntity(CouponDTO dto);
}
