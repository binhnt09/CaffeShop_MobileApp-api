package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.entity.User_;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import com.example.ceffeshop_mobileappdev.service.criteria.UserCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserQueryService extends QueryService<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserQueryService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findByCriteria(UserCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification).stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, page).map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(UserCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<User> specification = createSpecification(criteria);
        return userRepository.count(specification);
    }

    protected Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), User_.id));
            }
        }
        return specification;
    }
}
