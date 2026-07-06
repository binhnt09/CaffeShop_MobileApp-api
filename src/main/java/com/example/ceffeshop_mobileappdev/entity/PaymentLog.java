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
@Table(name = "PaymentLogs")
public class PaymentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order orderID;

    @Size(max = 100)
    @NotNull
    @Column(name = "TransactionReference", nullable = false, length = 100)
    private String transactionReference;

    @Size(max = 20)
    @NotNull
    @Column(name = "GatewayName", nullable = false, length = 20)
    private String gatewayName;

    @NotNull
    @Column(name = "Amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Size(max = 10)
    @NotNull
    @Column(name = "ResponseCode", nullable = false, length = 10)
    private String responseCode;

    @Nationalized
    @Lob
    @Column(name = "Payload")
    private String payload;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;


}