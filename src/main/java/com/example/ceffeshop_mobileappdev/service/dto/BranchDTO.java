package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;

@Data
public class BranchDTO {
    private Integer id;
    private String branchName;
    private String address;
    private String phone;
    private String managerName;
    private String status;
}
