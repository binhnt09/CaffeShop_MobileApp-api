package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.repository.BranchRepository;
import com.example.ceffeshop_mobileappdev.service.BranchService;
import com.example.ceffeshop_mobileappdev.service.BranchQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.BranchCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.BranchDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.BranchMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;
    private final BranchQueryService branchQueryService;
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchDTO>>> getAllBranches(BranchCriteria criteria) {
        List<BranchDTO> branches = branchQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(branches, "Lấy danh sách chi nhánh thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> getBranch(@PathVariable("id") Integer id) {
        return branchService.findOne(id)
                .map(branchDTO -> ResponseEntity.ok(ApiResponse.success(branchDTO, "Lấy thông tin chi nhánh thành công")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Không tìm thấy chi nhánh với id: " + id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BranchDTO>> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        if (branchDTO.getId() != null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Một chi nhánh mới không được phép có ID trước"));
        }
        BranchDTO result = branchService.save(branchDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Tạo chi nhánh mới thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(
            @PathVariable("id") Integer id,
            @Valid @RequestBody BranchDTO branchDTO
    ) {
        if (branchDTO.getId() == null) {
            branchDTO.setId(id);
        } else if (!branchDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID trong URL và Request Body không khớp"));
        }

        if (branchService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy chi nhánh với id: " + id));
        }

        BranchDTO result = branchService.save(branchDTO);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật chi nhánh thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable("id") Integer id) {
        if (branchService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy chi nhánh với id: " + id));
        }
        branchService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa chi nhánh thành công"));
    }

    @GetMapping("/open")
    public ResponseEntity<ApiResponse<List<BranchDTO>>> getOpenBranches() {
        List<BranchDTO> branches = branchRepository.findByStatus("Open")
                .stream().map(branchMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success(branches, "Lấy danh sách chi nhánh đang mở thành công"));
    }
}
