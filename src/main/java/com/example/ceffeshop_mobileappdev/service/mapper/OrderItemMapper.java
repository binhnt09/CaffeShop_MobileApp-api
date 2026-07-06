package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.OrderItem;
import com.example.ceffeshop_mobileappdev.service.dto.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDto(OrderItem entity);
    OrderItem toEntity(OrderItemDTO dto);
}
