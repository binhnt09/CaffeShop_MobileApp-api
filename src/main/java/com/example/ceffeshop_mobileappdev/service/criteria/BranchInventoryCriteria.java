package com.example.ceffeshop_mobileappdev.service.criteria;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

@Data
@NoArgsConstructor
@ParameterObject
public class BranchInventoryCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter branchId;
    private IntegerFilter ingredientId;
    private StringFilter ingredientName;
    private BigDecimalFilter quantityAvailable;
    private InstantFilter lastUpdated;
    private Boolean distinct;

    public BranchInventoryCriteria(BranchInventoryCriteria other) {
        this.branchId = other.branchId == null ? null : other.branchId.copy();
        this.ingredientId = other.ingredientId == null ? null : other.ingredientId.copy();
        this.ingredientName = other.ingredientName == null ? null : other.ingredientName.copy();
        this.quantityAvailable = other.quantityAvailable == null ? null : other.quantityAvailable.copy();
        this.lastUpdated = other.lastUpdated == null ? null : other.lastUpdated.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BranchInventoryCriteria copy() {
        return new BranchInventoryCriteria(this);
    }
}
