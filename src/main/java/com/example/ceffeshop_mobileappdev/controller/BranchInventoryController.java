package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.service.BranchInventoryService;
import com.example.ceffeshop_mobileappdev.service.BranchInventoryQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.BranchInventoryCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.BranchInventoryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch-inventories")
@RequiredArgsConstructor
public class BranchInventoryController {

    private final BranchInventoryService branchInventoryService;
    private final BranchInventoryQueryService branchInventoryQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BranchInventoryDTO>>> getAllBranchInventories(BranchInventoryCriteria criteria) {
        List<BranchInventoryDTO> list = branchInventoryQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(list, "Lấy danh sách tồn kho chi nhánh thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BranchInventoryDTO>> updateInventory(@Valid @RequestBody BranchInventoryDTO dto) {
        BranchInventoryDTO result = branchInventoryService.save(dto);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật tồn kho thành công"));
    }
}
