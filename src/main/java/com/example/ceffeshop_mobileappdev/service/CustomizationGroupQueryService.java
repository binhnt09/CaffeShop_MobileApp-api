package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import com.example.ceffeshop_mobileappdev.repository.CustomizationGroupRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationGroupCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomizationGroupQueryService extends QueryService<CustomizationGroup> {

    private final CustomizationGroupRepository customizationGroupRepository;
    private final CustomizationGroupMapper customizationGroupMapper;

    public List<CustomizationGroupDTO> findByCriteria(CustomizationGroupCriteria criteria) {
        final Specification<CustomizationGroup> specification = createSpecification(criteria);
        return customizationGroupRepository.findAll(specification).stream()
                .map(customizationGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CustomizationGroupDTO> findByCriteria(CustomizationGroupCriteria criteria, Pageable page) {
        final Specification<CustomizationGroup> specification = createSpecification(criteria);
        return customizationGroupRepository.findAll(specification, page).map(customizationGroupMapper::toDto);
    }

    protected Specification<CustomizationGroup> createSpecification(CustomizationGroupCriteria criteria) {
        Specification<CustomizationGroup> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), CustomizationGroup_.id),
                buildStringSpecification(criteria.getGroupName(), CustomizationGroup_.groupName),
                buildRangeSpecification(criteria.getMaxOptions(), CustomizationGroup_.maxSelect)
            );
        }
        return specification;
    }
}
