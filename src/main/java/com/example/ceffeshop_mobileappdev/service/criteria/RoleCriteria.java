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
public class RoleCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter roleName;
    private StringFilter description;
    private Boolean distinct;

    public RoleCriteria(RoleCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.roleName = other.roleName == null ? null : other.roleName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RoleCriteria copy() {
        return new RoleCriteria(this);
    }
}
