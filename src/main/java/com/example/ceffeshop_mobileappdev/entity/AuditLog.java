package com.example.ceffeshop_mobileappdev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "AuditLogs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuditLogID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "TableName", nullable = false, length = 50)
    private String tableName;

    @Size(max = 10)
    @NotNull
    @Column(name = "\"Action\"", nullable = false, length = 10)
    private String action;

    @NotNull
    @Column(name = "RecordID", nullable = false)
    private Integer recordID;

    @Nationalized
    @Lob
    @Column(name = "OldData")
    private String oldData;

    @Nationalized
    @Lob
    @Column(name = "NewData")
    private String newData;

    @Column(name = "ChangedByUserID")
    private Integer changedByUserID;

    @ColumnDefault("getdate()")
    @Column(name = "ChangedAt")
    private Instant changedAt;


}