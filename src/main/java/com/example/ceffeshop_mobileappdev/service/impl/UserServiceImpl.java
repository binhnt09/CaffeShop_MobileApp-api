package com.example.ceffeshop_mobileappdev.service.impl;

import com.example.ceffeshop_mobileappdev.entity.Customer;
import com.example.ceffeshop_mobileappdev.entity.Role;
import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.repository.CustomerRepository;
import com.example.ceffeshop_mobileappdev.repository.RoleRepository;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import com.example.ceffeshop_mobileappdev.service.UserService;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.dto.request.RegisterRequest;
import com.example.ceffeshop_mobileappdev.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
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

        // Auto create Customer profile if Role is CUSTOMER (5)
        if (role.getId() == 5) {
            Customer customer = new Customer();
            customer.setUserID(savedUser);
            customer.setLoyaltyPoints(0);
            customer.setMembershipTier("BRONZE");
            customerRepository.save(customer);
        }

        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserDTO registerUser(RegisterRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(request.getFullName());
        userDTO.setEmail(request.getEmail());
        userDTO.setPhone(request.getPhone());
        userDTO.setPassword(request.getPassword());
        userDTO.setRoleId(5); // Default CUSTOMER
        return createUser(userDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public String requestPasswordReset(String email) {
        User user = userRepository.findOneByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản với email: " + email));

        // Generate 6-digit OTP code for simulation/email send
        return String.format("%06d", new Random().nextInt(900000) + 100000);
    }

    @Override
    @Transactional
    public void finishPasswordReset(String emailOrKey, String newPassword) {
        User user = userRepository.findOneByEmailIgnoreCase(emailOrKey)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại trong hệ thống"));
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findOneByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản người dùng"));

        if (!passwordEncoder.matches(currentPassword, user.getPasswordHash())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }
}
