package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.PaymentLog;
import com.example.ceffeshop_mobileappdev.entity.PaymentLog_;
import com.example.ceffeshop_mobileappdev.repository.PaymentLogRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.PaymentLogCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.PaymentLogDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.PaymentLogMapper;
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
public class PaymentLogQueryService extends QueryService<PaymentLog> {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentLogQueryService.class);

    private final PaymentLogRepository paymentLogRepository;
    private final PaymentLogMapper paymentLogMapper;

    public PaymentLogQueryService(PaymentLogRepository paymentLogRepository, PaymentLogMapper paymentLogMapper) {
        this.paymentLogRepository = paymentLogRepository;
        this.paymentLogMapper = paymentLogMapper;
    }

    @Transactional(readOnly = true)
    public List<PaymentLogDTO> findByCriteria(PaymentLogCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<PaymentLog> specification = createSpecification(criteria);
        return paymentLogRepository.findAll(specification).stream().map(paymentLogMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PaymentLogDTO> findByCriteria(PaymentLogCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PaymentLog> specification = createSpecification(criteria);
        return paymentLogRepository.findAll(specification, page).map(paymentLogMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(PaymentLogCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<PaymentLog> specification = createSpecification(criteria);
        return paymentLogRepository.count(specification);
    }

    protected Specification<PaymentLog> createSpecification(PaymentLogCriteria criteria) {
        Specification<PaymentLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PaymentLog_.id));
            }
        }
        return specification;
    }
}
