package com.example.ceffeshop_mobileappdev.service;

import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.dto.request.RegisterRequest;

public interface UserService {
    UserDTO createUser(UserDTO request);
    UserDTO registerUser(RegisterRequest request);
    String requestPasswordReset(String email);
    void finishPasswordReset(String emailOrKey, String newPassword);
    void changePassword(String email, String currentPassword, String newPassword);
}
