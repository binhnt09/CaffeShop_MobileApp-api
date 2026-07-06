package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;

import java.util.Optional;

public interface BranchService {
    BranchDTO save(BranchDTO dto);
    Optional<BranchDTO> findOne(Integer id);
    void delete(Integer id);
}
