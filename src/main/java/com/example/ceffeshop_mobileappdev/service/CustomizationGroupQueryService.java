package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup_;
import com.example.ceffeshop_mobileappdev.repository.CustomizationGroupRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationGroupCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationGroupMapper;
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
public class CustomizationGroupQueryService extends QueryService<CustomizationGroup> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomizationGroupQueryService.class);

    private final CustomizationGroupRepository customizationGroupRepository;
    private final CustomizationGroupMapper customizationGroupMapper;

    public CustomizationGroupQueryService(CustomizationGroupRepository customizationGroupRepository, CustomizationGroupMapper customizationGroupMapper) {
        this.customizationGroupRepository = customizationGroupRepository;
        this.customizationGroupMapper = customizationGroupMapper;
    }

    @Transactional(readOnly = true)
    public List<CustomizationGroupDTO> findByCriteria(CustomizationGroupCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<CustomizationGroup> specification = createSpecification(criteria);
        return customizationGroupRepository.findAll(specification).stream().map(customizationGroupMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CustomizationGroupDTO> findByCriteria(CustomizationGroupCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CustomizationGroup> specification = createSpecification(criteria);
        return customizationGroupRepository.findAll(specification, page).map(customizationGroupMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CustomizationGroupCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<CustomizationGroup> specification = createSpecification(criteria);
        return customizationGroupRepository.count(specification);
    }

    protected Specification<CustomizationGroup> createSpecification(CustomizationGroupCriteria criteria) {
        Specification<CustomizationGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CustomizationGroup_.id));
            }
        }
        return specification;
    }
}
