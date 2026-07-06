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
public class OrderCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter customerId;
    private StringFilter customerName;
    private IntegerFilter branchId;
    private IntegerFilter cashierId;
    private IntegerFilter shiftId;
    private StringFilter orderType;
    private StringFilter status;
    private BigDecimalFilter totalAmount;
    private BigDecimalFilter finalAmount;
    private StringFilter paymentMethod;
    private StringFilter paymentStatus;
    private InstantFilter createdAt;
    private Boolean distinct;

    public OrderCriteria(OrderCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.customerName = other.customerName == null ? null : other.customerName.copy();
        this.branchId = other.branchId == null ? null : other.branchId.copy();
        this.cashierId = other.cashierId == null ? null : other.cashierId.copy();
        this.shiftId = other.shiftId == null ? null : other.shiftId.copy();
        this.orderType = other.orderType == null ? null : other.orderType.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.totalAmount = other.totalAmount == null ? null : other.totalAmount.copy();
        this.finalAmount = other.finalAmount == null ? null : other.finalAmount.copy();
        this.paymentMethod = other.paymentMethod == null ? null : other.paymentMethod.copy();
        this.paymentStatus = other.paymentStatus == null ? null : other.paymentStatus.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OrderCriteria copy() {
        return new OrderCriteria(this);
    }
}
