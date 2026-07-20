package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.CustomizationOption;
import com.example.ceffeshop_mobileappdev.entity.CustomizationOption_;
import com.example.ceffeshop_mobileappdev.repository.CustomizationOptionRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationOptionCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationOptionMapper;
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
public class CustomizationOptionQueryService extends QueryService<CustomizationOption> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomizationOptionQueryService.class);

    private final CustomizationOptionRepository customizationOptionRepository;
    private final CustomizationOptionMapper customizationOptionMapper;

    public CustomizationOptionQueryService(CustomizationOptionRepository customizationOptionRepository, CustomizationOptionMapper customizationOptionMapper) {
        this.customizationOptionRepository = customizationOptionRepository;
        this.customizationOptionMapper = customizationOptionMapper;
    }

    @Transactional(readOnly = true)
    public List<CustomizationOptionDTO> findByCriteria(CustomizationOptionCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<CustomizationOption> specification = createSpecification(criteria);
        return customizationOptionRepository.findAll(specification).stream().map(customizationOptionMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CustomizationOptionDTO> findByCriteria(CustomizationOptionCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CustomizationOption> specification = createSpecification(criteria);
        return customizationOptionRepository.findAll(specification, page).map(customizationOptionMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CustomizationOptionCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<CustomizationOption> specification = createSpecification(criteria);
        return customizationOptionRepository.count(specification);
    }

    protected Specification<CustomizationOption> createSpecification(CustomizationOptionCriteria criteria) {
        Specification<CustomizationOption> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CustomizationOption_.id));
            }
        }
        return specification;
    }
}
