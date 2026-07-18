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
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "CategoryName", nullable = false, length = 100)
    private String categoryName;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @ColumnDefault("'Active'")
    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Description", length = 255)
    private String description;

    @Size(max = 255)
    @Column(name = "ImageUrl", length = 255)
    private String imageUrl;
}