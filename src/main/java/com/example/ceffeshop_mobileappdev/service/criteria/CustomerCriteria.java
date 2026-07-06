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
public class CustomerCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter userId;
    private StringFilter userFullName;
    private IntegerFilter loyaltyPoints;
    private StringFilter tier;
    private InstantFilter createdAt;
    private Boolean distinct;

    public CustomerCriteria(CustomerCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.userFullName = other.userFullName == null ? null : other.userFullName.copy();
        this.loyaltyPoints = other.loyaltyPoints == null ? null : other.loyaltyPoints.copy();
        this.tier = other.tier == null ? null : other.tier.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomerCriteria copy() {
        return new CustomerCriteria(this);
    }
}
