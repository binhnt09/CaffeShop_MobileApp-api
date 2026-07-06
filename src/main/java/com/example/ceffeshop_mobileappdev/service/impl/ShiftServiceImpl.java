package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.ShiftService;
import com.example.ceffeshop_mobileappdev.service.dto.ShiftDTO;
import com.example.ceffeshop_mobileappdev.entity.Shift;

import com.example.ceffeshop_mobileappdev.repository.ShiftRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.ShiftMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    @Override
    public ShiftDTO save(ShiftDTO dto) {
        Shift entity = shiftMapper.toEntity(dto);
        entity = shiftRepository.save(entity);
        return shiftMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShiftDTO> findOne(Integer id) {
        return shiftRepository.findById(id).map(shiftMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        shiftRepository.deleteById(id);
    }
}
