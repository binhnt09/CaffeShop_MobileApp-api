package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Order;
import com.example.ceffeshop_mobileappdev.repository.OrderRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.OrderCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Order_;
import com.example.ceffeshop_mobileappdev.entity.User_;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import com.example.ceffeshop_mobileappdev.entity.Customer_;
import com.example.ceffeshop_mobileappdev.entity.Shift_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService extends QueryService<Order> {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> findByCriteria(OrderCriteria criteria) {
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.findAll(specification).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<OrderDTO> findByCriteria(OrderCriteria criteria, Pageable page) {
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.findAll(specification, page).map(orderMapper::toDto);
    }

    protected Specification<Order> createSpecification(OrderCriteria criteria) {
        Specification<Order> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Order_.id),
                buildSpecification(criteria.getCustomerId(), root -> root.join(Order_.customerID, JoinType.LEFT).get(Customer_.id)),
                buildSpecification(criteria.getCustomerName(), root -> root.join(Order_.customerID, JoinType.LEFT).join(Customer_.userID, JoinType.LEFT).get(User_.fullName)),
                buildSpecification(criteria.getBranchId(), root -> root.join(Order_.branchID, JoinType.LEFT).get(Branch_.id)),
                buildSpecification(criteria.getCashierId(), root -> root.join(Order_.cashierID, JoinType.LEFT).get(User_.id)),
                buildSpecification(criteria.getShiftId(), root -> root.join(Order_.shiftID, JoinType.LEFT).get(Shift_.id)),
                buildStringSpecification(criteria.getOrderType(), Order_.orderSource),
                buildStringSpecification(criteria.getStatus(), Order_.orderStatus),
                buildRangeSpecification(criteria.getTotalAmount(), Order_.totalAmount),
                buildRangeSpecification(criteria.getFinalAmount(), Order_.finalAmount),
                buildStringSpecification(criteria.getPaymentMethod(), Order_.paymentMethod),
                buildStringSpecification(criteria.getPaymentStatus(), Order_.paymentStatus),
                buildRangeSpecification(criteria.getCreatedAt(), Order_.createdAt)
            );
        }
        return specification;
    }
}
