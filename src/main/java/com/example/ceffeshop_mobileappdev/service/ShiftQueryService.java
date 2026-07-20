package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Shift;
import com.example.ceffeshop_mobileappdev.entity.Shift_;
import com.example.ceffeshop_mobileappdev.repository.ShiftRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.ShiftCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ShiftDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.ShiftMapper;
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
public class ShiftQueryService extends QueryService<Shift> {

    private static final Logger LOG = LoggerFactory.getLogger(ShiftQueryService.class);

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    public ShiftQueryService(ShiftRepository shiftRepository, ShiftMapper shiftMapper) {
        this.shiftRepository = shiftRepository;
        this.shiftMapper = shiftMapper;
    }

    @Transactional(readOnly = true)
    public List<ShiftDTO> findByCriteria(ShiftCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Shift> specification = createSpecification(criteria);
        return shiftRepository.findAll(specification).stream().map(shiftMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ShiftDTO> findByCriteria(ShiftCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Shift> specification = createSpecification(criteria);
        return shiftRepository.findAll(specification, page).map(shiftMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ShiftCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Shift> specification = createSpecification(criteria);
        return shiftRepository.count(specification);
    }

    protected Specification<Shift> createSpecification(ShiftCriteria criteria) {
        Specification<Shift> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Shift_.id));
            }
        }
        return specification;
    }
}
