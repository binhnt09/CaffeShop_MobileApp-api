package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "LoyaltyPoints", nullable = false)
    private Integer loyaltyPoints;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Bronze'")
    @Column(name = "MembershipTier", nullable = false, length = 20)
    private String membershipTier;


}