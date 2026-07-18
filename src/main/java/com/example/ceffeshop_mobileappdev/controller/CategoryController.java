package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.service.CategoryService;
import com.example.ceffeshop_mobileappdev.service.CategoryQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.CategoryCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.CategoryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryQueryService categoryQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories(CategoryCriteria criteria) {
        List<CategoryDTO> categories = categoryQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(categories, "Lấy danh sách danh mục thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategory(@PathVariable("id") Integer id) {
        return categoryService.findOne(id)
                .map(categoryDTO -> ResponseEntity.ok(ApiResponse.success(categoryDTO, "Lấy thông tin danh mục thành công")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Không tìm thấy danh mục với id: " + id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Một danh mục mới không được phép có ID trước"));
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Tạo danh mục mới thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        if (categoryDTO.getId() == null) {
            categoryDTO.setId(id);
        } else if (!categoryDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID trong URL và Request Body không khớp"));
        }

        if (categoryService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy danh mục với id: " + id));
        }

        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật danh mục thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable("id") Integer id) {
        if (categoryService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy danh mục với id: " + id));
        }
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa danh mục thành công"));
    }
}
