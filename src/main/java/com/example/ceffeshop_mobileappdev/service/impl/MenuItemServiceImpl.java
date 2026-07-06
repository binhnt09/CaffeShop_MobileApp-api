package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.MenuItemService;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import com.example.ceffeshop_mobileappdev.entity.MenuItem;

import com.example.ceffeshop_mobileappdev.repository.MenuItemRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public MenuItemDTO save(MenuItemDTO dto) {
        MenuItem entity = menuItemMapper.toEntity(dto);
        entity = menuItemRepository.save(entity);
        return menuItemMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuItemDTO> findOne(Integer id) {
        return menuItemRepository.findById(id).map(menuItemMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        menuItemRepository.deleteById(id);
    }
}
