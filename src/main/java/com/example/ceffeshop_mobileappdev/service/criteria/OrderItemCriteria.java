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
public class OrderItemCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter orderId;
    private IntegerFilter menuItemId;
    private StringFilter itemName;
    private IntegerFilter quantity;
    private BigDecimalFilter unitPrice;
    private Boolean distinct;

    public OrderItemCriteria(OrderItemCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
        this.menuItemId = other.menuItemId == null ? null : other.menuItemId.copy();
        this.itemName = other.itemName == null ? null : other.itemName.copy();
        this.quantity = other.quantity == null ? null : other.quantity.copy();
        this.unitPrice = other.unitPrice == null ? null : other.unitPrice.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OrderItemCriteria copy() {
        return new OrderItemCriteria(this);
    }
}
