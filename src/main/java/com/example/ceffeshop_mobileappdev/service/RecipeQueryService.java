package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Recipe;
import com.example.ceffeshop_mobileappdev.entity.Recipe_;
import com.example.ceffeshop_mobileappdev.repository.RecipeRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.RecipeCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.RecipeMapper;
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
public class RecipeQueryService extends QueryService<Recipe> {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeQueryService.class);

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeQueryService(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Transactional(readOnly = true)
    public List<RecipeDTO> findByCriteria(RecipeCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.findAll(specification).stream().map(recipeMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<RecipeDTO> findByCriteria(RecipeCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.findAll(specification, page).map(recipeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(RecipeCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.count(specification);
    }

    protected Specification<Recipe> createSpecification(RecipeCriteria criteria) {
        Specification<Recipe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMenuItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getMenuItemId(), root -> root.get("id").get("menuItemID")));
            }
        }
        return specification;
    }
}
