package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.InventoryAudit;
import com.example.ceffeshop_mobileappdev.repository.InventoryAuditRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.InventoryAuditCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.InventoryAuditDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.InventoryAuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.InventoryAudit_;
import com.example.ceffeshop_mobileappdev.entity.User_;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryAuditQueryService extends QueryService<InventoryAudit> {

    private final InventoryAuditRepository inventoryAuditRepository;
    private final InventoryAuditMapper inventoryAuditMapper;

    public List<InventoryAuditDTO> findByCriteria(InventoryAuditCriteria criteria) {
        final Specification<InventoryAudit> specification = createSpecification(criteria);
        return inventoryAuditRepository.findAll(specification).stream()
                .map(inventoryAuditMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<InventoryAuditDTO> findByCriteria(InventoryAuditCriteria criteria, Pageable page) {
        final Specification<InventoryAudit> specification = createSpecification(criteria);
        return inventoryAuditRepository.findAll(specification, page).map(inventoryAuditMapper::toDto);
    }

    protected Specification<InventoryAudit> createSpecification(InventoryAuditCriteria criteria) {
        Specification<InventoryAudit> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), InventoryAudit_.id),
                buildSpecification(criteria.getBranchId(), root -> root.join(InventoryAudit_.branchID, JoinType.LEFT).get(Branch_.id)),
                buildSpecification(criteria.getManagerId(), root -> root.join(InventoryAudit_.managerID, JoinType.LEFT).get(User_.id)),
                buildRangeSpecification(criteria.getAuditDate(), InventoryAudit_.auditDate),
                buildStringSpecification(criteria.getStatus(), InventoryAudit_.status)
            );
        }
        return specification;
    }
}
