package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class UserBranchId implements Serializable {
    private static final long serialVersionUID = 162337417629213055L;
    @NotNull
    @Column(name = "UserID", nullable = false)
    private Integer userID;

    @NotNull
    @Column(name = "BranchID", nullable = false)
    private Integer branchID;


}