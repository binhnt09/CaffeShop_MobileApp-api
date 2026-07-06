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
public class CustomizationOptionCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter groupId;
    private StringFilter optionName;
    private BigDecimalFilter extraPrice;
    private BooleanFilter isAvailable;
    private Boolean distinct;

    public CustomizationOptionCriteria(CustomizationOptionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.optionName = other.optionName == null ? null : other.optionName.copy();
        this.extraPrice = other.extraPrice == null ? null : other.extraPrice.copy();
        this.isAvailable = other.isAvailable == null ? null : other.isAvailable.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomizationOptionCriteria copy() {
        return new CustomizationOptionCriteria(this);
    }
}
