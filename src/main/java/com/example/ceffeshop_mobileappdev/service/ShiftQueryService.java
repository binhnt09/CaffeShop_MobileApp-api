package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Shift;
import com.example.ceffeshop_mobileappdev.repository.ShiftRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.ShiftCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ShiftDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.ShiftMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Shift_;
import com.example.ceffeshop_mobileappdev.entity.User_;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShiftQueryService extends QueryService<Shift> {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    public List<ShiftDTO> findByCriteria(ShiftCriteria criteria) {
        final Specification<Shift> specification = createSpecification(criteria);
        return shiftRepository.findAll(specification).stream()
                .map(shiftMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<ShiftDTO> findByCriteria(ShiftCriteria criteria, Pageable page) {
        final Specification<Shift> specification = createSpecification(criteria);
        return shiftRepository.findAll(specification, page).map(shiftMapper::toDto);
    }

    protected Specification<Shift> createSpecification(ShiftCriteria criteria) {
        Specification<Shift> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Shift_.id),
                buildSpecification(criteria.getBranchId(), root -> root.join(Shift_.branchID, JoinType.LEFT).get(Branch_.id)),
                buildSpecification(criteria.getCashierId(), root -> root.join(Shift_.cashierID, JoinType.LEFT).get(User_.id)),
                buildRangeSpecification(criteria.getStartTime(), Shift_.startTime),
                buildRangeSpecification(criteria.getEndTime(), Shift_.endTime),
                buildStringSpecification(criteria.getStatus(), Shift_.status)
            );
        }
        return specification;
    }
}
