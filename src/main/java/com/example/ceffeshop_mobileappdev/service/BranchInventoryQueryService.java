package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.BranchInventory;
import com.example.ceffeshop_mobileappdev.repository.BranchInventoryRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.BranchInventoryCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.BranchInventoryDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchInventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.BranchInventory_;
import com.example.ceffeshop_mobileappdev.entity.Branch_;
import com.example.ceffeshop_mobileappdev.entity.Ingredient_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BranchInventoryQueryService extends QueryService<BranchInventory> {

    private final BranchInventoryRepository branchInventoryRepository;
    private final BranchInventoryMapper branchInventoryMapper;

    public List<BranchInventoryDTO> findByCriteria(BranchInventoryCriteria criteria) {
        final Specification<BranchInventory> specification = createSpecification(criteria);
        return branchInventoryRepository.findAll(specification).stream()
                .map(branchInventoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<BranchInventoryDTO> findByCriteria(BranchInventoryCriteria criteria, Pageable page) {
        final Specification<BranchInventory> specification = createSpecification(criteria);
        return branchInventoryRepository.findAll(specification, page).map(branchInventoryMapper::toDto);
    }

    protected Specification<BranchInventory> createSpecification(BranchInventoryCriteria criteria) {
        // ĐÃ SỬA: Thay Specification.unrestricted() thành Specification.where(null) chuẩn Spring Data JPA
        Specification<BranchInventory> specification = Specification.where(null);
        if (criteria != null) {
            specification = Specification.allOf(
                    Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.where(null),
                    buildSpecification(criteria.getBranchId(), root -> root.join(BranchInventory_.branchID, JoinType.LEFT).get(Branch_.id)),
                    buildSpecification(criteria.getIngredientId(), root -> root.join(BranchInventory_.ingredientID, JoinType.LEFT).get(Ingredient_.id)),
                    buildSpecification(criteria.getIngredientName(), root -> root.join(BranchInventory_.ingredientID, JoinType.LEFT).get(Ingredient_.ingredientName)),
                    buildRangeSpecification(criteria.getQuantityAvailable(), BranchInventory_.currentStock),
                    buildRangeSpecification(criteria.getLastUpdated(), BranchInventory_.updatedAt)
            );
        }
        return specification;
    }
}