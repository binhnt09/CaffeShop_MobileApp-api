package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.OrderService;
import com.example.ceffeshop_mobileappdev.service.dto.OrderDTO;
import com.example.ceffeshop_mobileappdev.entity.Order;

import com.example.ceffeshop_mobileappdev.repository.OrderRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO save(OrderDTO dto) {
        Order entity = orderMapper.toEntity(dto);
        entity = orderRepository.save(entity);
        return orderMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Integer id) {
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
