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
public class CouponCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter code;
    private StringFilter description;
    private StringFilter discountType;
    private BigDecimalFilter discountValue;
    private BigDecimalFilter minOrderAmount;
    private BigDecimalFilter maxDiscountAmount;
    private InstantFilter startDate;
    private InstantFilter endDate;
    private IntegerFilter usageLimit;
    private IntegerFilter usedCount;
    private StringFilter status;
    private Boolean distinct;

    public CouponCriteria(CouponCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.discountType = other.discountType == null ? null : other.discountType.copy();
        this.discountValue = other.discountValue == null ? null : other.discountValue.copy();
        this.minOrderAmount = other.minOrderAmount == null ? null : other.minOrderAmount.copy();
        this.maxDiscountAmount = other.maxDiscountAmount == null ? null : other.maxDiscountAmount.copy();
        this.startDate = other.startDate == null ? null : other.startDate.copy();
        this.endDate = other.endDate == null ? null : other.endDate.copy();
        this.usageLimit = other.usageLimit == null ? null : other.usageLimit.copy();
        this.usedCount = other.usedCount == null ? null : other.usedCount.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CouponCriteria copy() {
        return new CouponCriteria(this);
    }
}
