package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.CouponService;
import com.example.ceffeshop_mobileappdev.service.dto.CouponDTO;
import com.example.ceffeshop_mobileappdev.entity.Coupon;

import com.example.ceffeshop_mobileappdev.repository.CouponRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public CouponDTO save(CouponDTO dto) {
        Coupon entity = couponMapper.toEntity(dto);
        entity = couponRepository.save(entity);
        return couponMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CouponDTO> findOne(Integer id) {
        return couponRepository.findById(id).map(couponMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        couponRepository.deleteById(id);
    }
}
