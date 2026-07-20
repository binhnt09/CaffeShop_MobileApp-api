package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Order;
import com.example.ceffeshop_mobileappdev.entity.Order_;
import com.example.ceffeshop_mobileappdev.repository.OrderRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.OrderCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderMapper;
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
public class OrderQueryService extends QueryService<Order> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderQueryService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderQueryService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findByCriteria(OrderCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.findAll(specification).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findByCriteria(OrderCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.findAll(specification, page).map(orderMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(OrderCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.count(specification);
    }

    protected Specification<Order> createSpecification(OrderCriteria criteria) {
        Specification<Order> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Order_.id));
            }
        }
        return specification;
    }
}
