package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {
    OrderDTO save(OrderDTO dto);
    Optional<OrderDTO> findOne(Integer id);
    void delete(Integer id);
}
