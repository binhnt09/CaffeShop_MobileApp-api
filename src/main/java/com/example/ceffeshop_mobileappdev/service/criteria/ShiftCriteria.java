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
public class ShiftCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter branchId;
    private IntegerFilter cashierId;
    private InstantFilter startTime;
    private InstantFilter endTime;
    private BigDecimalFilter totalSales;
    private StringFilter status;
    private Boolean distinct;

    public ShiftCriteria(ShiftCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.branchId = other.branchId == null ? null : other.branchId.copy();
        this.cashierId = other.cashierId == null ? null : other.cashierId.copy();
        this.startTime = other.startTime == null ? null : other.startTime.copy();
        this.endTime = other.endTime == null ? null : other.endTime.copy();
        this.totalSales = other.totalSales == null ? null : other.totalSales.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ShiftCriteria copy() {
        return new ShiftCriteria(this);
    }
}
