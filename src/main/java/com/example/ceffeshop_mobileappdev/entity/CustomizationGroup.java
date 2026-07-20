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
@Table(name = "CustomizationGroups")
public class CustomizationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroupID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "GroupName", nullable = false, length = 100)
    private String groupName;

    @ColumnDefault("0")
    @Column(name = "MinSelect")
    private Integer minSelect;

    @ColumnDefault("1")
    @Column(name = "MaxSelect")
    private Integer maxSelect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getMinSelect() {
        return minSelect;
    }

    public void setMinSelect(Integer minSelect) {
        this.minSelect = minSelect;
    }

    public Integer getMaxSelect() {
        return maxSelect;
    }

    public void setMaxSelect(Integer maxSelect) {
        this.maxSelect = maxSelect;
    }
}