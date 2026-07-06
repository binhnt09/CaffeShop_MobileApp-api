package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.CategoryService;
import com.example.ceffeshop_mobileappdev.service.dto.CategoryDTO;
import com.example.ceffeshop_mobileappdev.entity.Category;

import com.example.ceffeshop_mobileappdev.repository.CategoryRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        Category entity = categoryMapper.toEntity(dto);
        entity = categoryRepository.save(entity);
        return categoryMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOne(Integer id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
