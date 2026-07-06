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


}