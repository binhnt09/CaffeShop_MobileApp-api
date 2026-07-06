package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.OrderItemDTO;

import java.util.Optional;

public interface OrderItemService {
    OrderItemDTO save(OrderItemDTO dto);
    Optional<OrderItemDTO> findOne(Integer id);
    void delete(Integer id);
}
