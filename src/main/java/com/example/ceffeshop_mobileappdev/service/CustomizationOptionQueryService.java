package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.CustomizationOption;
import com.example.ceffeshop_mobileappdev.repository.CustomizationOptionRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationOptionCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.CustomizationOption_;
import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomizationOptionQueryService extends QueryService<CustomizationOption> {

    private final CustomizationOptionRepository customizationOptionRepository;
    private final CustomizationOptionMapper customizationOptionMapper;

    public List<CustomizationOptionDTO> findByCriteria(CustomizationOptionCriteria criteria) {
        final Specification<CustomizationOption> specification = createSpecification(criteria);
        return customizationOptionRepository.findAll(specification).stream()
                .map(customizationOptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CustomizationOptionDTO> findByCriteria(CustomizationOptionCriteria criteria, Pageable page) {
        final Specification<CustomizationOption> specification = createSpecification(criteria);
        return customizationOptionRepository.findAll(specification, page).map(customizationOptionMapper::toDto);
    }

    protected Specification<CustomizationOption> createSpecification(CustomizationOptionCriteria criteria) {
        Specification<CustomizationOption> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), CustomizationOption_.id),
                buildSpecification(criteria.getGroupId(), root -> root.join(CustomizationOption_.groupID, JoinType.LEFT).get(CustomizationGroup_.id)),
                buildStringSpecification(criteria.getOptionName(), CustomizationOption_.optionName),
                buildRangeSpecification(criteria.getExtraPrice(), CustomizationOption_.extraPrice),
                buildSpecification(criteria.getIsAvailable(), CustomizationOption_.isAvailable)
            );
        }
        return specification;
    }
}
