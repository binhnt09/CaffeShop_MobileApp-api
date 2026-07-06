package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "InventoryAudits")
public class InventoryAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuditID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ManagerID", nullable = false)
    private User managerID;

    @NotNull
    @ColumnDefault("getdate()")
    @Column(name = "AuditDate", nullable = false)
    private Instant auditDate;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Draft'")
    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "TotalVarianceValue", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalVarianceValue;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;


}