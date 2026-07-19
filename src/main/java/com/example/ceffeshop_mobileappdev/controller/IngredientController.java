package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.entity.Ingredient;
import com.example.ceffeshop_mobileappdev.repository.IngredientRepository;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ingredient>>> getAllIngredients() {
        List<Ingredient> list = ingredientRepository.findAll();
        return ResponseEntity.ok(ApiResponse.success(list, "Lấy danh sách nguyên liệu thành công"));
    }
}
