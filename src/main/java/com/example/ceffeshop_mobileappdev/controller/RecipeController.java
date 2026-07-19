package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.service.RecipeService;
import com.example.ceffeshop_mobileappdev.service.RecipeQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.RecipeCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.RecipeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeQueryService recipeQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipeDTO>>> getAllRecipes(RecipeCriteria criteria) {
        List<RecipeDTO> list = recipeQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(list, "Lấy công thức thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecipeDTO>> updateRecipe(@Valid @RequestBody RecipeDTO dto) {
        RecipeDTO result = recipeService.save(dto);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật công thức thành công"));
    }
}
