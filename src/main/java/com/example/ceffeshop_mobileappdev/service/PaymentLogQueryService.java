package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.PaymentLog;
import com.example.ceffeshop_mobileappdev.repository.PaymentLogRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.PaymentLogCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.PaymentLogDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.PaymentLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.PaymentLog_;
import com.example.ceffeshop_mobileappdev.entity.Order_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentLogQueryService extends QueryService<PaymentLog> {

    private final PaymentLogRepository paymentLogRepository;
    private final PaymentLogMapper paymentLogMapper;

    public List<PaymentLogDTO> findByCriteria(PaymentLogCriteria criteria) {
        final Specification<PaymentLog> specification = createSpecification(criteria);
        return paymentLogRepository.findAll(specification).stream()
                .map(paymentLogMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<PaymentLogDTO> findByCriteria(PaymentLogCriteria criteria, Pageable page) {
        final Specification<PaymentLog> specification = createSpecification(criteria);
        return paymentLogRepository.findAll(specification, page).map(paymentLogMapper::toDto);
    }

    protected Specification<PaymentLog> createSpecification(PaymentLogCriteria criteria) {
        Specification<PaymentLog> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), PaymentLog_.id),
                buildSpecification(criteria.getOrderId(), root -> root.join(PaymentLog_.orderID, JoinType.LEFT).get(Order_.id)),
                buildRangeSpecification(criteria.getAmount(), PaymentLog_.amount),
                buildStringSpecification(criteria.getTransactionId(), PaymentLog_.transactionReference),
                buildRangeSpecification(criteria.getCreatedAt(), PaymentLog_.createdAt)
            );
        }
        return specification;
    }
}
