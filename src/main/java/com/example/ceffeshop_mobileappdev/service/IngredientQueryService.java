package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Ingredient;
import com.example.ceffeshop_mobileappdev.repository.IngredientRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.IngredientCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.IngredientDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Ingredient_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientQueryService extends QueryService<Ingredient> {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDTO> findByCriteria(IngredientCriteria criteria) {
        final Specification<Ingredient> specification = createSpecification(criteria);
        return ingredientRepository.findAll(specification).stream()
                .map(ingredientMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<IngredientDTO> findByCriteria(IngredientCriteria criteria, Pageable page) {
        final Specification<Ingredient> specification = createSpecification(criteria);
        return ingredientRepository.findAll(specification, page).map(ingredientMapper::toDto);
    }

    protected Specification<Ingredient> createSpecification(IngredientCriteria criteria) {
        Specification<Ingredient> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Ingredient_.id),
                buildStringSpecification(criteria.getName(), Ingredient_.ingredientName),
                buildStringSpecification(criteria.getUnit(), Ingredient_.unitType)
            );
        }
        return specification;
    }
}
