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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}