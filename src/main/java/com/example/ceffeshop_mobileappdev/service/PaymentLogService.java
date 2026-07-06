package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.PaymentLogDTO;

import java.util.Optional;

public interface PaymentLogService {
    PaymentLogDTO save(PaymentLogDTO dto);
    Optional<PaymentLogDTO> findOne(Integer id);
    void delete(Integer id);
}
