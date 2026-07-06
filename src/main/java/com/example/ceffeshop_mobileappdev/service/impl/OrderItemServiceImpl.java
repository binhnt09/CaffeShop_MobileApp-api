package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.OrderItemService;
import com.example.ceffeshop_mobileappdev.service.dto.OrderItemDTO;
import com.example.ceffeshop_mobileappdev.entity.OrderItem;

import com.example.ceffeshop_mobileappdev.repository.OrderItemRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDTO save(OrderItemDTO dto) {
        OrderItem entity = orderItemMapper.toEntity(dto);
        entity = orderItemRepository.save(entity);
        return orderItemMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItemDTO> findOne(Integer id) {
        return orderItemRepository.findById(id).map(orderItemMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
