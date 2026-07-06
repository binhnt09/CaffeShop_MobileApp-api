package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "PasswordResets")
public class PasswordReset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResetID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @Size(max = 6)
    @NotNull
    @Column(name = "OTPCode", nullable = false, length = 6)
    private String oTPCode;

    @NotNull
    @Column(name = "ExpiresAt", nullable = false)
    private Instant expiresAt;

    @ColumnDefault("0")
    @Column(name = "IsUsed")
    private Boolean isUsed;

    @ColumnDefault("getdate()")
    @Column(name = "CreatedAt")
    private Instant createdAt;


}