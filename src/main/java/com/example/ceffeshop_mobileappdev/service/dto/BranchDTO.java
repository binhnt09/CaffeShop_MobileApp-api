package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class BranchDTO {
    private Integer id;
    private String branchName;
    private String address;
    private String phone;
    private String managerName;
    private String status;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
