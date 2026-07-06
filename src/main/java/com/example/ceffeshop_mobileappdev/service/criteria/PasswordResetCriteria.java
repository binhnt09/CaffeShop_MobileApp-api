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
public class PasswordResetCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private IntegerFilter id;
    private StringFilter email;
    private InstantFilter expiresAt;
    private InstantFilter createdAt;
    private Boolean distinct;

    public PasswordResetCriteria(PasswordResetCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.expiresAt = other.expiresAt == null ? null : other.expiresAt.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PasswordResetCriteria copy() {
        return new PasswordResetCriteria(this);
    }
}
