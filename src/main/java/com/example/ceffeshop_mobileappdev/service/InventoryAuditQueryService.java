package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.InventoryAudit;
import com.example.ceffeshop_mobileappdev.entity.InventoryAudit_;
import com.example.ceffeshop_mobileappdev.repository.InventoryAuditRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.InventoryAuditCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.InventoryAuditDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.InventoryAuditMapper;
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
public class InventoryAuditQueryService extends QueryService<InventoryAudit> {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryAuditQueryService.class);

    private final InventoryAuditRepository inventoryAuditRepository;
    private final InventoryAuditMapper inventoryAuditMapper;

    public InventoryAuditQueryService(InventoryAuditRepository inventoryAuditRepository, InventoryAuditMapper inventoryAuditMapper) {
        this.inventoryAuditRepository = inventoryAuditRepository;
        this.inventoryAuditMapper = inventoryAuditMapper;
    }

    @Transactional(readOnly = true)
    public List<InventoryAuditDTO> findByCriteria(InventoryAuditCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<InventoryAudit> specification = createSpecification(criteria);
        return inventoryAuditRepository.findAll(specification).stream().map(inventoryAuditMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<InventoryAuditDTO> findByCriteria(InventoryAuditCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InventoryAudit> specification = createSpecification(criteria);
        return inventoryAuditRepository.findAll(specification, page).map(inventoryAuditMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(InventoryAuditCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<InventoryAudit> specification = createSpecification(criteria);
        return inventoryAuditRepository.count(specification);
    }

    protected Specification<InventoryAudit> createSpecification(InventoryAuditCriteria criteria) {
        Specification<InventoryAudit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), InventoryAudit_.id));
            }
        }
        return specification;
    }
}
