package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.ShiftDTO;

import java.util.Optional;

public interface ShiftService {
    ShiftDTO save(ShiftDTO dto);
    Optional<ShiftDTO> findOne(Integer id);
    void delete(Integer id);
}
