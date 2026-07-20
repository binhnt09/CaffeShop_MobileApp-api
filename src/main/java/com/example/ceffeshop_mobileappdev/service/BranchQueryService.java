package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Branch;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import com.example.ceffeshop_mobileappdev.repository.BranchRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.BranchCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchMapper;
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
public class BranchQueryService extends QueryService<Branch> {

    private static final Logger LOG = LoggerFactory.getLogger(BranchQueryService.class);

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchQueryService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Transactional(readOnly = true)
    public List<BranchDTO> findByCriteria(BranchCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Branch> specification = createSpecification(criteria);
        return branchRepository.findAll(specification).stream().map(branchMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<BranchDTO> findByCriteria(BranchCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Branch> specification = createSpecification(criteria);
        return branchRepository.findAll(specification, page).map(branchMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(BranchCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Branch> specification = createSpecification(criteria);
        return branchRepository.count(specification);
    }

    protected Specification<Branch> createSpecification(BranchCriteria criteria) {
        Specification<Branch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Branch_.id));
            }
        }
        return specification;
    }
}
