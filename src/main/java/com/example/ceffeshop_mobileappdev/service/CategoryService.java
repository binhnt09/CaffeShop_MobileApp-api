package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.CategoryDTO;

import java.util.Optional;

public interface CategoryService {
    CategoryDTO save(CategoryDTO dto);
    Optional<CategoryDTO> findOne(Integer id);
    void delete(Integer id);
}
