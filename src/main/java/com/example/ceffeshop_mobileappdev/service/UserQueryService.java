package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.entity.User_;
import com.example.ceffeshop_mobileappdev.entity.Role_;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.UserCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import jakarta.persistence.criteria.JoinType;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService extends QueryService<User> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findByCriteria(UserCriteria criteria) {
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable page) {
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, page).map(userMapper::toDto);
    }

    protected Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.unrestricted();
        if (criteria != null) {
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : Specification.unrestricted(),
                buildRangeSpecification(criteria.getId(), User_.id),
                buildStringSpecification(criteria.getFullName(), User_.fullName),
                buildStringSpecification(criteria.getEmail(), User_.email),
                buildStringSpecification(criteria.getPhone(), User_.phone),
                buildStringSpecification(criteria.getStatus(), User_.status),
                buildRangeSpecification(criteria.getCreatedAt(), User_.createdAt),
                buildSpecification(criteria.getRoleId(), root -> root.join(User_.roleID, JoinType.LEFT).get(Role_.id)),
                buildSpecification(criteria.getRoleName(), root -> root.join(User_.roleID, JoinType.LEFT).get(Role_.roleName))
            );
        }
        return specification;
    }
}
