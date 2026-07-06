package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Branch;
import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDTO toDto(Branch entity);
    Branch toEntity(BranchDTO dto);
}
