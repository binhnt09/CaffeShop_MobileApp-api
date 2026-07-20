package com.example.ceffeshop_mobileappdev.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordFinishRequest {

    @NotBlank(message = "Mã xác thực/Email không được để trống")
    private String key;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, message = "Mật khẩu mới phải từ 6 ký tự trở lên")
    private String newPassword;
}
