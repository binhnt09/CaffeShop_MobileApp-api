package com.example.ceffeshop_mobileappdev.service.mapper;

import com.example.ceffeshop_mobileappdev.entity.Customer;
import com.example.ceffeshop_mobileappdev.service.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDto(Customer entity);
    Customer toEntity(CustomerDTO dto);
}
