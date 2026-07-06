package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "UserBranches")
public class UserBranch {
    @EmbeddedId
    private UserBranchId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @MapsId("branchID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @ColumnDefault("getdate()")
    @Column(name = "AssignedAt")
    private Instant assignedAt;


}