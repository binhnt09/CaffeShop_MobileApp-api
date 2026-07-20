package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import com.example.ceffeshop_mobileappdev.entity.MenuItem_;
import com.example.ceffeshop_mobileappdev.repository.MenuItemRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.MenuItemCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.MenuItemMapper;
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
public class MenuItemQueryService extends QueryService<MenuItem> {

    private static final Logger LOG = LoggerFactory.getLogger(MenuItemQueryService.class);

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemQueryService(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Transactional(readOnly = true)
    public List<MenuItemDTO> findByCriteria(MenuItemCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<MenuItem> specification = createSpecification(criteria);
        return menuItemRepository.findAll(specification).stream().map(menuItemMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<MenuItemDTO> findByCriteria(MenuItemCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MenuItem> specification = createSpecification(criteria);
        return menuItemRepository.findAll(specification, page).map(menuItemMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(MenuItemCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<MenuItem> specification = createSpecification(criteria);
        return menuItemRepository.count(specification);
    }

    protected Specification<MenuItem> createSpecification(MenuItemCriteria criteria) {
        Specification<MenuItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MenuItem_.id));
            }
        }
        return specification;
    }
}
