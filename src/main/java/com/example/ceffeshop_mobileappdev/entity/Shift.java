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
@Table(name = "Shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShiftID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CashierID", nullable = false)
    private User cashierID;

    @NotNull
    @ColumnDefault("getdate()")
    @Column(name = "StartTime", nullable = false)
    private Instant startTime;

    @Column(name = "EndTime")
    private Instant endTime;

    @NotNull
    @Column(name = "OpeningBalance", nullable = false, precision = 18, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "ClosingBalance", precision = 18, scale = 2)
    private BigDecimal closingBalance;

    @Column(name = "ActualBalance", precision = 18, scale = 2)
    private BigDecimal actualBalance;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Open'")
    @Column(name = "Status", nullable = false, length = 20)
    private String status;


}