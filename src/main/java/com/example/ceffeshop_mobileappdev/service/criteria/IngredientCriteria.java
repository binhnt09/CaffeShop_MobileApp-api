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
public class IngredientCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter name;
    private StringFilter unit;
    private IntegerFilter reorderLevel;
    private Boolean distinct;

    public IngredientCriteria(IngredientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.unit = other.unit == null ? null : other.unit.copy();
        this.reorderLevel = other.reorderLevel == null ? null : other.reorderLevel.copy();
        this.distinct = other.distinct;
    }

    @Override
    public IngredientCriteria copy() {
        return new IngredientCriteria(this);
    }
}
