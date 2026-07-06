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
public class RecipeCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter menuItemId;
    private IntegerFilter ingredientId;
    private StringFilter ingredientName;
    private BigDecimalFilter quantityRequired;
    private Boolean distinct;

    public RecipeCriteria(RecipeCriteria other) {
        this.menuItemId = other.menuItemId == null ? null : other.menuItemId.copy();
        this.ingredientId = other.ingredientId == null ? null : other.ingredientId.copy();
        this.ingredientName = other.ingredientName == null ? null : other.ingredientName.copy();
        this.quantityRequired = other.quantityRequired == null ? null : other.quantityRequired.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RecipeCriteria copy() {
        return new RecipeCriteria(this);
    }
}
