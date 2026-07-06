package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.Customer;
import com.example.ceffeshop_mobileappdev.repository.CustomerRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomerCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomerDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import jakarta.persistence.criteria.JoinType;
import com.example.ceffeshop_mobileappdev.entity.Customer_;
import com.example.ceffeshop_mobileappdev.entity.User_;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerQueryService extends QueryService<Customer> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> findByCriteria(CustomerCriteria criteria) {
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.findAll(specification).stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<CustomerDTO> findByCriteria(CustomerCriteria criteria, Pageable page) {
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.findAll(specification, page).map(customerMapper::toDto);
    }

    protected Specification<Customer> createSpecification(CustomerCriteria criteria) {
        Specification<Customer> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), Customer_.id),
                buildSpecification(criteria.getUserId(), root -> root.join(Customer_.userID, JoinType.LEFT).get(User_.id)),
                buildSpecification(criteria.getUserFullName(), root -> root.join(Customer_.userID, JoinType.LEFT).get(User_.fullName)),
                buildRangeSpecification(criteria.getLoyaltyPoints(), Customer_.loyaltyPoints),
                buildStringSpecification(criteria.getTier(), Customer_.membershipTier),
                buildSpecification(criteria.getCreatedAt(), root -> root.join(Customer_.userID, JoinType.LEFT).get(User_.createdAt))
            );
        }
        return specification;
    }
}
