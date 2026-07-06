package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Coupon;
import com.example.ceffeshop_mobileappdev.repository.CouponRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CouponCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CouponDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Coupon_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponQueryService extends QueryService<Coupon> {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public List<CouponDTO> findByCriteria(CouponCriteria criteria) {
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.findAll(specification).stream()
                .map(couponMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CouponDTO> findByCriteria(CouponCriteria criteria, Pageable page) {
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.findAll(specification, page).map(couponMapper::toDto);
    }

    protected Specification<Coupon> createSpecification(CouponCriteria criteria) {
        Specification<Coupon> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Coupon_.id),
                buildStringSpecification(criteria.getCode(), Coupon_.code),
                buildStringSpecification(criteria.getDiscountType(), Coupon_.discountType),
                buildRangeSpecification(criteria.getDiscountValue(), Coupon_.discountValue),
                buildRangeSpecification(criteria.getMinOrderAmount(), Coupon_.minOrderValue),
                buildRangeSpecification(criteria.getMaxDiscountAmount(), Coupon_.maxDiscountAmount),
                buildRangeSpecification(criteria.getStartDate(), Coupon_.startDate),
                buildRangeSpecification(criteria.getEndDate(), Coupon_.endDate),
                buildRangeSpecification(criteria.getUsageLimit(), Coupon_.usageLimit),
                buildRangeSpecification(criteria.getUsedCount(), Coupon_.usedCount)
            );
        }
        return specification;
    }
}
