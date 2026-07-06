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
@Table(name = "Coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CouponID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "Code", nullable = false, length = 20)
    private String code;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "DiscountType", nullable = false, length = 20)
    private String discountType;

    @NotNull
    @Column(name = "DiscountValue", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountValue;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "MinOrderValue", nullable = false, precision = 18, scale = 2)
    private BigDecimal minOrderValue;

    @Column(name = "MaxDiscountAmount", precision = 18, scale = 2)
    private BigDecimal maxDiscountAmount;

    @NotNull
    @Column(name = "StartDate", nullable = false)
    private Instant startDate;

    @NotNull
    @Column(name = "EndDate", nullable = false)
    private Instant endDate;

    @NotNull
    @Column(name = "UsageLimit", nullable = false)
    private Integer usageLimit;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "UsedCount", nullable = false)
    private Integer usedCount;

    @ColumnDefault("1")
    @Column(name = "IsActive")
    private Boolean isActive;


}