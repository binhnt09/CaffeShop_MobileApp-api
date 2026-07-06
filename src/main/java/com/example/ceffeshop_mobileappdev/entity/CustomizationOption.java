package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "CustomizationOptions")
public class CustomizationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OptionID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GroupID", nullable = false)
    private CustomizationGroup groupID;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "OptionName", nullable = false, length = 100)
    private String optionName;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "ExtraPrice", nullable = false, precision = 18, scale = 2)
    private BigDecimal extraPrice;

    @ColumnDefault("1")
    @Column(name = "IsAvailable")
    private Boolean isAvailable;


}