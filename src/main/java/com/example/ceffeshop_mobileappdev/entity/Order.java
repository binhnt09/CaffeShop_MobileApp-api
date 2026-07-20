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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "OrderCode", nullable = false, length = 50)
    private String orderCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BranchID", nullable = false)
    private Branch branchID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")
    private Customer customerID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CashierID")
    private User cashierID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShiftID")
    private Shift shiftID;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "OrderSource", nullable = false, length = 20)
    private String orderSource;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "FulfillmentMode", nullable = false, length = 20)
    private String fulfillmentMode;

    @NotNull
    @Column(name = "TotalAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "VATAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal vATAmount;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "DiscountAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountAmount;

    @NotNull
    @Column(name = "FinalAmount", nullable = false, precision = 18, scale = 2)
    private BigDecimal finalAmount;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Pending'")
    @Column(name = "OrderStatus", nullable = false, length = 20)
    private String orderStatus;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Unpaid'")
    @Column(name = "PaymentStatus", nullable = false, length = 20)
    private String paymentStatus;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "PaymentMethod", nullable = false, length = 20)
    private String paymentMethod;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @ColumnDefault("getdate()")
    @Column(name = "UpdatedAt")
    private Instant updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Branch getBranchID() {
        return branchID;
    }

    public void setBranchID(Branch branchID) {
        this.branchID = branchID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public User getCashierID() {
        return cashierID;
    }

    public void setCashierID(User cashierID) {
        this.cashierID = cashierID;
    }

    public Shift getShiftID() {
        return shiftID;
    }

    public void setShiftID(Shift shiftID) {
        this.shiftID = shiftID;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getFulfillmentMode() {
        return fulfillmentMode;
    }

    public void setFulfillmentMode(String fulfillmentMode) {
        this.fulfillmentMode = fulfillmentMode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getVATAmount() {
        return vATAmount;
    }

    public void setVATAmount(BigDecimal vATAmount) {
        this.vATAmount = vATAmount;
    }

    public BigDecimal getvATAmount() {
        return vATAmount;
    }

    public void setvATAmount(BigDecimal vATAmount) {
        this.vATAmount = vATAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}