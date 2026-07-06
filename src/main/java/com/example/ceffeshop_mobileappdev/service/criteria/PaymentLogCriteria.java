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
public class PaymentLogCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter orderId;
    private StringFilter paymentMethod;
    private BigDecimalFilter amount;
    private StringFilter status;
    private StringFilter transactionId;
    private InstantFilter createdAt;
    private Boolean distinct;

    public PaymentLogCriteria(PaymentLogCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
        this.paymentMethod = other.paymentMethod == null ? null : other.paymentMethod.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PaymentLogCriteria copy() {
        return new PaymentLogCriteria(this);
    }
}
