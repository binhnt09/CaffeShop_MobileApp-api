package com.example.ceffeshop_mobileappdev.service.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class PasswordResetDTO {
    private Integer id;
    private String email;
    private Instant expiresAt;
    private Instant createdAt;
}
