package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.UserDTO;
import com.example.ceffeshop_mobileappdev.service.UserService;
import com.example.ceffeshop_mobileappdev.service.UserQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.UserCriteria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers(UserCriteria criteria) {
        List<UserDTO> users = userQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(users, "Lấy danh sách người dùng thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO request) {
        UserDTO createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(createdUser, "Tạo người dùng mới thành công"));
    }
}
