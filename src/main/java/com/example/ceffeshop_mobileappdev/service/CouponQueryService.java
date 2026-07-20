package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Coupon;
import com.example.ceffeshop_mobileappdev.entity.Coupon_;
import com.example.ceffeshop_mobileappdev.repository.CouponRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CouponCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CouponDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CouponMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CouponQueryService extends QueryService<Coupon> {

    private static final Logger LOG = LoggerFactory.getLogger(CouponQueryService.class);

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponQueryService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    @Transactional(readOnly = true)
    public List<CouponDTO> findByCriteria(CouponCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.findAll(specification).stream().map(couponMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CouponDTO> findByCriteria(CouponCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.findAll(specification, page).map(couponMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CouponCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.count(specification);
    }

    protected Specification<Coupon> createSpecification(CouponCriteria criteria) {
        Specification<Coupon> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Coupon_.id));
            }
        }
        return specification;
    }
}
