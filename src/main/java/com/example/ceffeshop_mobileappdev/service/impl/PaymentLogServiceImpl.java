package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.PaymentLogService;
import com.example.ceffeshop_mobileappdev.service.dto.PaymentLogDTO;
import com.example.ceffeshop_mobileappdev.entity.PaymentLog;

import com.example.ceffeshop_mobileappdev.repository.PaymentLogRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.PaymentLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentLogServiceImpl implements PaymentLogService {

    private final PaymentLogRepository paymentLogRepository;
    private final PaymentLogMapper paymentLogMapper;

    @Override
    public PaymentLogDTO save(PaymentLogDTO dto) {
        PaymentLog entity = paymentLogMapper.toEntity(dto);
        entity = paymentLogRepository.save(entity);
        return paymentLogMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentLogDTO> findOne(Integer id) {
        return paymentLogRepository.findById(id).map(paymentLogMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        paymentLogRepository.deleteById(id);
    }
}
