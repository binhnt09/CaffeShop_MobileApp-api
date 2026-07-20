package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.OrderItem;
import com.example.ceffeshop_mobileappdev.entity.OrderItem_;
import com.example.ceffeshop_mobileappdev.repository.OrderItemRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.OrderItemCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.OrderItemDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderItemMapper;
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
public class OrderItemQueryService extends QueryService<OrderItem> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderItemQueryService.class);

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemQueryService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional(readOnly = true)
    public List<OrderItemDTO> findByCriteria(OrderItemCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<OrderItem> specification = createSpecification(criteria);
        return orderItemRepository.findAll(specification).stream().map(orderItemMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<OrderItemDTO> findByCriteria(OrderItemCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderItem> specification = createSpecification(criteria);
        return orderItemRepository.findAll(specification, page).map(orderItemMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(OrderItemCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<OrderItem> specification = createSpecification(criteria);
        return orderItemRepository.count(specification);
    }

    protected Specification<OrderItem> createSpecification(OrderItemCriteria criteria) {
        Specification<OrderItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrderItem_.id));
            }
        }
        return specification;
    }
}
