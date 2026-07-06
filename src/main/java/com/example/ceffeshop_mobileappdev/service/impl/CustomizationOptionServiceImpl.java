package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.CustomizationOptionService;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;
import com.example.ceffeshop_mobileappdev.entity.CustomizationOption;

import com.example.ceffeshop_mobileappdev.repository.CustomizationOptionRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomizationOptionServiceImpl implements CustomizationOptionService {

    private final CustomizationOptionRepository customizationOptionRepository;
    private final CustomizationOptionMapper customizationOptionMapper;

    @Override
    public CustomizationOptionDTO save(CustomizationOptionDTO dto) {
        CustomizationOption entity = customizationOptionMapper.toEntity(dto);
        entity = customizationOptionRepository.save(entity);
        return customizationOptionMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomizationOptionDTO> findOne(Integer id) {
        return customizationOptionRepository.findById(id).map(customizationOptionMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        customizationOptionRepository.deleteById(id);
    }
}
