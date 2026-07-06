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
public class MenuItemCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter name;
    private BigDecimalFilter basePrice;
    private IntegerFilter categoryId;
    private StringFilter categoryName;
    private StringFilter status;
    private InstantFilter createdAt;
    private Boolean distinct;

    public MenuItemCriteria(MenuItemCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.basePrice = other.basePrice == null ? null : other.basePrice.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.categoryName = other.categoryName == null ? null : other.categoryName.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MenuItemCriteria copy() {
        return new MenuItemCriteria(this);
    }
}
