package com.example.ceffeshop_mobileappdev;

import com.example.ceffeshop_mobileappdev.entity.Role;
import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.repository.RoleRepository;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@SpringBootApplication
public class CeffeShopMobileAppDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(CeffeShopMobileAppDevApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                // Check / Create ADMIN Role (id = 1)
                Role adminRole = roleRepository.findById(1).orElseGet(() -> {
                    Role role = new Role();
                    role.setId(1);
                    role.setRoleName("ADMIN");
                    role.setDescription("System Administrator");
                    return roleRepository.save(role);
                });

                // Check / Create CUSTOMER Role (id = 5)
                roleRepository.findById(5).orElseGet(() -> {
                    Role role = new Role();
                    role.setId(5);
                    role.setRoleName("CUSTOMER");
                    role.setDescription("Mobile App Customer");
                    return roleRepository.save(role);
                });

                // Check / Create Admin User
                if (!userRepository.existsByEmail("admin@coffee.com")) {
                    User admin = new User();
                    admin.setFullName("Vũ Quốc Admin");
                    admin.setEmail("admin@coffee.com");
                    admin.setPhone("0977777777");
                    admin.setPasswordHash(passwordEncoder.encode("123456"));
                    admin.setRoleID(adminRole);
                    admin.setStatus("Active");
                    admin.setCreatedAt(Instant.now());
                    admin.setUpdatedAt(Instant.now());
                    userRepository.save(admin);
                    System.out.println(">>> Đã khởi tạo thành công tài khoản admin@coffee.com / 123456 trong DB!");
                } else {
                    userRepository.findOneByEmailIgnoreCase("admin@coffee.com").ifPresent(admin -> {
                        admin.setPasswordHash(passwordEncoder.encode("123456"));
                        admin.setStatus("Active");
                        userRepository.save(admin);
                        System.out.println(">>> Đã cập nhật đảm bảo mật khẩu admin@coffee.com là 123456!");
                    });
                }
            } catch (Exception e) {
                System.err.println("Không thể khởi tạo dữ liệu mặc định: " + e.getMessage());
            }
        };
    }
}
