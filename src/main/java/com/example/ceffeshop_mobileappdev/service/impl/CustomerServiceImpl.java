package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.service.CustomerService;
import com.example.ceffeshop_mobileappdev.service.dto.CustomerDTO;
import com.example.ceffeshop_mobileappdev.entity.Customer;

import com.example.ceffeshop_mobileappdev.repository.CustomerRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO save(CustomerDTO dto) {
        Customer entity = customerMapper.toEntity(dto);
        entity = customerRepository.save(entity);
        return customerMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(Integer id) {
        return customerRepository.findById(id).map(customerMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
