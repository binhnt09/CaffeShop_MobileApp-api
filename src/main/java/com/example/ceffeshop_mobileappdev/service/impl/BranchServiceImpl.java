package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.BranchService;
import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;
import com.example.ceffeshop_mobileappdev.entity.Branch;

import com.example.ceffeshop_mobileappdev.repository.BranchRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    public BranchDTO save(BranchDTO dto) {
        Branch entity = branchMapper.toEntity(dto);
        entity = branchRepository.save(entity);
        return branchMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchDTO> findOne(Integer id) {
        return branchRepository.findById(id).map(branchMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        branchRepository.deleteById(id);
    }
}
