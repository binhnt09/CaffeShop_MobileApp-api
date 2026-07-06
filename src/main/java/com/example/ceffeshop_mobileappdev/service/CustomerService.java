package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.CustomerDTO;

import java.util.Optional;

public interface CustomerService {
    CustomerDTO save(CustomerDTO dto);
    Optional<CustomerDTO> findOne(Integer id);
    void delete(Integer id);
}
