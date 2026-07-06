package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.entity.Role;
import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.repository.RoleRepository;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import com.example.ceffeshop_mobileappdev.service.UserService;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống");
        }

        Role role = roleRepository.findById(request.getRoleId() != null ? request.getRoleId() : 5)
                .orElseThrow(() -> new RuntimeException("Role không tồn tại"));

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRoleID(role);
        user.setStatus("Active");
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
