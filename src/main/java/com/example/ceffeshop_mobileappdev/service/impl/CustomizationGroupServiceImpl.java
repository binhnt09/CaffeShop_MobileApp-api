package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.CustomizationGroupService;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;
import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;

import com.example.ceffeshop_mobileappdev.repository.CustomizationGroupRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomizationGroupServiceImpl implements CustomizationGroupService {

    private final CustomizationGroupRepository customizationGroupRepository;
    private final CustomizationGroupMapper customizationGroupMapper;

    @Override
    public CustomizationGroupDTO save(CustomizationGroupDTO dto) {
        CustomizationGroup entity = customizationGroupMapper.toEntity(dto);
        entity = customizationGroupRepository.save(entity);
        return customizationGroupMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomizationGroupDTO> findOne(Integer id) {
        return customizationGroupRepository.findById(id).map(customizationGroupMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        customizationGroupRepository.deleteById(id);
    }
}
