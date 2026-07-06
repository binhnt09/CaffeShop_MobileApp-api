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
public class CustomizationGroupCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter groupName;
    private StringFilter description;
    private BooleanFilter isRequired;
    private IntegerFilter maxOptions;
    private Boolean distinct;

    public CustomizationGroupCriteria(CustomizationGroupCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.groupName = other.groupName == null ? null : other.groupName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.isRequired = other.isRequired == null ? null : other.isRequired.copy();
        this.maxOptions = other.maxOptions == null ? null : other.maxOptions.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomizationGroupCriteria copy() {
        return new CustomizationGroupCriteria(this);
    }
}
