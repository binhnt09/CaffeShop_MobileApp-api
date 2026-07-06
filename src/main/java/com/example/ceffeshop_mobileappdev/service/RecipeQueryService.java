package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Recipe;
import com.example.ceffeshop_mobileappdev.repository.RecipeRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.RecipeCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Recipe_;
import com.example.ceffeshop_mobileappdev.entity.Ingredient_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeQueryService extends QueryService<Recipe> {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDTO> findByCriteria(RecipeCriteria criteria) {
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.findAll(specification).stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<RecipeDTO> findByCriteria(RecipeCriteria criteria, Pageable page) {
        final Specification<Recipe> specification = createSpecification(criteria);
        return recipeRepository.findAll(specification, page).map(recipeMapper::toDto);
    }

    protected Specification<Recipe> createSpecification(RecipeCriteria criteria) {
        Specification<Recipe> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildSpecification(criteria.getIngredientId(), root -> root.join(Recipe_.ingredientID, JoinType.LEFT).get(Ingredient_.id)),
                buildSpecification(criteria.getIngredientName(), root -> root.join(Recipe_.ingredientID, JoinType.LEFT).get(Ingredient_.ingredientName)),
                buildRangeSpecification(criteria.getQuantityRequired(), Recipe_.standardQuantity)
            );
        }
        return specification;
    }
}
