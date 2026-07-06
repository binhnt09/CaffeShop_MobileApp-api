package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.OrderItem;
import com.example.ceffeshop_mobileappdev.repository.OrderItemRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.OrderItemCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.OrderItemDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.OrderItem_;
import com.example.ceffeshop_mobileappdev.entity.Order_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemQueryService extends QueryService<OrderItem> {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemDTO> findByCriteria(OrderItemCriteria criteria) {
        final Specification<OrderItem> specification = createSpecification(criteria);
        return orderItemRepository.findAll(specification).stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<OrderItemDTO> findByCriteria(OrderItemCriteria criteria, Pageable page) {
        final Specification<OrderItem> specification = createSpecification(criteria);
        return orderItemRepository.findAll(specification, page).map(orderItemMapper::toDto);
    }

    protected Specification<OrderItem> createSpecification(OrderItemCriteria criteria) {
        Specification<OrderItem> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), OrderItem_.id),
                buildSpecification(criteria.getOrderId(), root -> root.join(OrderItem_.orderID, JoinType.LEFT).get(Order_.id)),
                buildRangeSpecification(criteria.getQuantity(), OrderItem_.quantity),
                buildRangeSpecification(criteria.getUnitPrice(), OrderItem_.unitPrice)
            );
        }
        return specification;
    }
}
