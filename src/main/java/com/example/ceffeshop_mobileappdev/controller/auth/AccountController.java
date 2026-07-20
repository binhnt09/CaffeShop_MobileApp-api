package com.example.ceffeshop_mobileappdev.controller.auth;

import com.example.ceffeshop_mobileappdev.service.UserService;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.dto.request.ChangePasswordRequest;
import com.example.ceffeshop_mobileappdev.service.dto.request.RegisterRequest;
import com.example.ceffeshop_mobileappdev.service.dto.request.ResetPasswordFinishRequest;
import com.example.ceffeshop_mobileappdev.service.dto.request.ResetPasswordInitRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    /**
     * 1.1 Chức năng Đăng ký tài khoản (Register)
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerAccount(@Valid @RequestBody RegisterRequest request) {
        UserDTO createdUser = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(createdUser, "Đăng ký tài khoản thành công!"));
    }

    /**
     * 1.3 Chức năng Khôi phục mật khẩu - Khởi tạo (Forgot Password Init)
     */
    @PostMapping("/account/reset-password/init")
    public ResponseEntity<ApiResponse<Map<String, String>>> requestPasswordReset(@Valid @RequestBody ResetPasswordInitRequest request) {
        String otpCode = userService.requestPasswordReset(request.getEmail());
        Map<String, String> data = new HashMap<>();
        data.put("email", request.getEmail());
        data.put("otpCode", otpCode); // Trong thực tế sẽ gửi qua Email/SMS
        return ResponseEntity.ok(ApiResponse.success(data, "Mã xác thực khôi phục mật khẩu đã được tạo"));
    }

    /**
     * 1.3 Chức năng Khôi phục mật khẩu - Hoàn thành (Reset Password Finish)
     */
    @PostMapping("/account/reset-password/finish")
    public ResponseEntity<ApiResponse<Void>> finishPasswordReset(@Valid @RequestBody ResetPasswordFinishRequest request) {
        userService.finishPasswordReset(request.getKey(), request.getNewPassword());
        return ResponseEntity.ok(ApiResponse.success(null, "Đặt lại mật khẩu thành công!"));
    }

    /**
     * 1.4 Chức năng Đổi mật khẩu (Change Password)
     */
    @PostMapping("/account/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Vui lòng đăng nhập để thực hiện đổi mật khẩu"));
        }
        userService.changePassword(principal.getName(), request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok(ApiResponse.success(null, "Đổi mật khẩu thành công!"));
    }
}
