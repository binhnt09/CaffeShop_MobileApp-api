package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Ingredient;
import com.example.ceffeshop_mobileappdev.entity.Ingredient_;
import com.example.ceffeshop_mobileappdev.repository.IngredientRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.IngredientCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.IngredientDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.IngredientMapper;
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
public class IngredientQueryService extends QueryService<Ingredient> {

    private static final Logger LOG = LoggerFactory.getLogger(IngredientQueryService.class);

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientQueryService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Transactional(readOnly = true)
    public List<IngredientDTO> findByCriteria(IngredientCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Ingredient> specification = createSpecification(criteria);
        return ingredientRepository.findAll(specification).stream().map(ingredientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<IngredientDTO> findByCriteria(IngredientCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ingredient> specification = createSpecification(criteria);
        return ingredientRepository.findAll(specification, page).map(ingredientMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(IngredientCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Ingredient> specification = createSpecification(criteria);
        return ingredientRepository.count(specification);
    }

    protected Specification<Ingredient> createSpecification(IngredientCriteria criteria) {
        Specification<Ingredient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Ingredient_.id));
            }
        }
        return specification;
    }
}
