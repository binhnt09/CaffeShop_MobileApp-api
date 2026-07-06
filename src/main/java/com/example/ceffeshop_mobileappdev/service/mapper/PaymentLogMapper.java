package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.PaymentLog;
import com.example.ceffeshop_mobileappdev.service.dto.PaymentLogDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentLogMapper {
    PaymentLogDTO toDto(PaymentLog entity);
    PaymentLog toEntity(PaymentLogDTO dto);
}
