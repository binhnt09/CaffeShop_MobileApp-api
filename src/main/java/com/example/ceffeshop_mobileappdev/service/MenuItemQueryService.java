package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import com.example.ceffeshop_mobileappdev.repository.MenuItemRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.MenuItemCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.MenuItem_;
import com.example.ceffeshop_mobileappdev.entity.Category_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuItemQueryService extends QueryService<MenuItem> {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public List<MenuItemDTO> findByCriteria(MenuItemCriteria criteria) {
        final Specification<MenuItem> specification = createSpecification(criteria);
        return menuItemRepository.findAll(specification).stream()
                .map(menuItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<MenuItemDTO> findByCriteria(MenuItemCriteria criteria, Pageable page) {
        final Specification<MenuItem> specification = createSpecification(criteria);
        return menuItemRepository.findAll(specification, page).map(menuItemMapper::toDto);
    }

    protected Specification<MenuItem> createSpecification(MenuItemCriteria criteria) {
        Specification<MenuItem> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), MenuItem_.id),
                buildStringSpecification(criteria.getName(), MenuItem_.itemName),
                buildRangeSpecification(criteria.getBasePrice(), MenuItem_.basePrice),
                buildSpecification(criteria.getCategoryId(), root -> root.join(MenuItem_.categoryID, JoinType.LEFT).get(Category_.id)),
                buildSpecification(criteria.getCategoryName(), root -> root.join(MenuItem_.categoryID, JoinType.LEFT).get(Category_.categoryName)),
                buildRangeSpecification(criteria.getCreatedAt(), MenuItem_.createdAt)
            );
        }
        return specification;
    }
}
