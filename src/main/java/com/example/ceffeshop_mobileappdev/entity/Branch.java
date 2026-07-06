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
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BranchID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "BranchName", nullable = false, length = 100)
    private String branchName;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "Latitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @NotNull
    @Column(name = "Longitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @NotNull
    @Column(name = "OpeningTime", nullable = false)
    private LocalTime openingTime;

    @NotNull
    @Column(name = "ClosingTime", nullable = false)
    private LocalTime closingTime;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Open'")
    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;


}