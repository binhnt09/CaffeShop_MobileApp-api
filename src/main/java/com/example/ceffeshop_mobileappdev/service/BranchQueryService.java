package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Branch;
import com.example.ceffeshop_mobileappdev.repository.BranchRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.BranchCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BranchQueryService extends QueryService<Branch> {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public List<BranchDTO> findByCriteria(BranchCriteria criteria) {
        final Specification<Branch> specification = createSpecification(criteria);
        return branchRepository.findAll(specification).stream()
                .map(branchMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<BranchDTO> findByCriteria(BranchCriteria criteria, Pageable page) {
        final Specification<Branch> specification = createSpecification(criteria);
        return branchRepository.findAll(specification, page).map(branchMapper::toDto);
    }

    protected Specification<Branch> createSpecification(BranchCriteria criteria) {
        Specification<Branch> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Branch_.id),
                buildStringSpecification(criteria.getBranchName(), Branch_.branchName),
                buildStringSpecification(criteria.getAddress(), Branch_.address),
                buildStringSpecification(criteria.getStatus(), Branch_.status)
            );
        }
        return specification;
    }
}
