package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;

import java.util.Optional;

public interface MenuItemService {
    MenuItemDTO save(MenuItemDTO dto);
    Optional<MenuItemDTO> findOne(Integer id);
    void delete(Integer id);
}
