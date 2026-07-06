package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Category;
import com.example.ceffeshop_mobileappdev.repository.CategoryRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CategoryCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CategoryDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Category_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryService extends QueryService<Category> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findByCriteria(CategoryCriteria criteria) {
        final Specification<Category> specification = createSpecification(criteria);
        return categoryRepository.findAll(specification).stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CategoryDTO> findByCriteria(CategoryCriteria criteria, Pageable page) {
        final Specification<Category> specification = createSpecification(criteria);
        return categoryRepository.findAll(specification, page).map(categoryMapper::toDto);
    }

    protected Specification<Category> createSpecification(CategoryCriteria criteria) {
        Specification<Category> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Category_.id),
                buildStringSpecification(criteria.getName(), Category_.categoryName)
            );
        }
        return specification;
    }
}
