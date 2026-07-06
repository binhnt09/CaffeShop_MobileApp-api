package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderItemCustomizationId implements Serializable {
    private static final long serialVersionUID = 4529907462792274641L;
    @NotNull
    @Column(name = "OrderItemID", nullable = false)
    private Integer orderItemID;

    @NotNull
    @Column(name = "OptionID", nullable = false)
    private Integer optionID;


}