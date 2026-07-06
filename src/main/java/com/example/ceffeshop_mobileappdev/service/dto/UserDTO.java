package com.example.ceffeshop_mobileappdev.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.Instant;

@Data
public class UserDTO {
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    private String fullName;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 10, max = 15, message = "Số điện thoại phải từ 10-15 số")
    private String phone;

    // Chỉ đọc khi request vào, không bao giờ xuất ra JSON lúc response
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Integer roleId;
    private String roleName;
    private String status;
    private Instant createdAt;
}
