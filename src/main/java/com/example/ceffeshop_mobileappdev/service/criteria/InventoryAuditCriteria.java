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
public class InventoryAuditCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private IntegerFilter branchId;
    private IntegerFilter managerId;
    private InstantFilter auditDate;
    private StringFilter status;
    private StringFilter remarks;
    private Boolean distinct;

    public InventoryAuditCriteria(InventoryAuditCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.branchId = other.branchId == null ? null : other.branchId.copy();
        this.managerId = other.managerId == null ? null : other.managerId.copy();
        this.auditDate = other.auditDate == null ? null : other.auditDate.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.distinct = other.distinct;
    }

    @Override
    public InventoryAuditCriteria copy() {
        return new InventoryAuditCriteria(this);
    }
}
