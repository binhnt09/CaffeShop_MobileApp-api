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
public class BranchCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter branchName;
    private StringFilter address;
    private StringFilter phone;
    private StringFilter managerName;
    private StringFilter status;
    private Boolean distinct;

    public BranchCriteria(BranchCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.branchName = other.branchName == null ? null : other.branchName.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.managerName = other.managerName == null ? null : other.managerName.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BranchCriteria copy() {
        return new BranchCriteria(this);
    }
}
