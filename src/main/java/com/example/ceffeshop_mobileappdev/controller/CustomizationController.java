package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.service.CustomizationGroupService;
import com.example.ceffeshop_mobileappdev.service.CustomizationGroupQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationGroupCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationGroupDTO;

import com.example.ceffeshop_mobileappdev.service.CustomizationOptionService;
import com.example.ceffeshop_mobileappdev.service.CustomizationOptionQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.CustomizationOptionCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.CustomizationOptionDTO;

import com.example.ceffeshop_mobileappdev.repository.MenuItemCustomizationRepository;
import com.example.ceffeshop_mobileappdev.service.mapper.CustomizationGroupMapper;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomizationController {

    private final CustomizationGroupService customizationGroupService;
    private final CustomizationGroupQueryService customizationGroupQueryService;

    private final CustomizationOptionService customizationOptionService;
    private final CustomizationOptionQueryService customizationOptionQueryService;

    private final MenuItemCustomizationRepository menuItemCustomizationRepository;
    private final CustomizationGroupMapper customizationGroupMapper;

    // --- Customization Groups ---

    @GetMapping("/customization-groups/menu-item/{id}")
    public ResponseEntity<ApiResponse<List<CustomizationGroupDTO>>> getCustomizationGroupsByMenuItem(@PathVariable("id") Integer menuItemId) {
        List<CustomizationGroupDTO> groups = menuItemCustomizationRepository.findGroupsByMenuItemId(menuItemId)
                .stream()
                .map(customizationGroupMapper::toDto)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(groups, "Lấy danh sách nhóm tùy chọn theo món thành công"));
    }

    @GetMapping("/customization-groups")
    public ResponseEntity<ApiResponse<List<CustomizationGroupDTO>>> getAllCustomizationGroups(CustomizationGroupCriteria criteria) {
        List<CustomizationGroupDTO> groups = customizationGroupQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(groups, "Lấy danh sách nhóm tùy chọn thành công"));
    }

    @GetMapping("/customization-groups/{id}")
    public ResponseEntity<ApiResponse<CustomizationGroupDTO>> getCustomizationGroup(@PathVariable("id") Integer id) {
        return customizationGroupService.findOne(id)
                .map(groupDTO -> ResponseEntity.ok(ApiResponse.success(groupDTO, "Lấy thông tin nhóm tùy chọn thành công")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Không tìm thấy nhóm tùy chọn với id: " + id)));
    }

    @PostMapping("/customization-groups")
    public ResponseEntity<ApiResponse<CustomizationGroupDTO>> createCustomizationGroup(@Valid @RequestBody CustomizationGroupDTO customizationGroupDTO) {
        if (customizationGroupDTO.getId() != null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Một nhóm tùy chọn mới không được phép có ID trước"));
        }
        CustomizationGroupDTO result = customizationGroupService.save(customizationGroupDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Tạo nhóm tùy chọn mới thành công"));
    }

    @PutMapping("/customization-groups/{id}")
    public ResponseEntity<ApiResponse<CustomizationGroupDTO>> updateCustomizationGroup(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CustomizationGroupDTO customizationGroupDTO
    ) {
        if (customizationGroupDTO.getId() == null) {
            customizationGroupDTO.setId(id);
        } else if (!customizationGroupDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID trong URL và Request Body không khớp"));
        }

        if (customizationGroupService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy nhóm tùy chọn với id: " + id));
        }

        CustomizationGroupDTO result = customizationGroupService.save(customizationGroupDTO);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật nhóm tùy chọn thành công"));
    }

    @DeleteMapping("/customization-groups/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomizationGroup(@PathVariable("id") Integer id) {
        if (customizationGroupService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy nhóm tùy chọn với id: " + id));
        }
        customizationGroupService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa nhóm tùy chọn thành công"));
    }

    // --- Customization Options ---

    @GetMapping("/customization-options")
    public ResponseEntity<ApiResponse<List<CustomizationOptionDTO>>> getAllCustomizationOptions(CustomizationOptionCriteria criteria) {
        List<CustomizationOptionDTO> options = customizationOptionQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(options, "Lấy danh sách tùy chọn thành công"));
    }

    @GetMapping("/customization-options/{id}")
    public ResponseEntity<ApiResponse<CustomizationOptionDTO>> getCustomizationOption(@PathVariable("id") Integer id) {
        return customizationOptionService.findOne(id)
                .map(optionDTO -> ResponseEntity.ok(ApiResponse.success(optionDTO, "Lấy thông tin tùy chọn thành công")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Không tìm thấy tùy chọn với id: " + id)));
    }

    @PostMapping("/customization-options")
    public ResponseEntity<ApiResponse<CustomizationOptionDTO>> createCustomizationOption(@Valid @RequestBody CustomizationOptionDTO customizationOptionDTO) {
        if (customizationOptionDTO.getId() != null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Một tùy chọn mới không được phép có ID trước"));
        }
        CustomizationOptionDTO result = customizationOptionService.save(customizationOptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Tạo tùy chọn mới thành công"));
    }

    @PutMapping("/customization-options/{id}")
    public ResponseEntity<ApiResponse<CustomizationOptionDTO>> updateCustomizationOption(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CustomizationOptionDTO customizationOptionDTO
    ) {
        if (customizationOptionDTO.getId() == null) {
            customizationOptionDTO.setId(id);
        } else if (!customizationOptionDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID trong URL và Request Body không khớp"));
        }

        if (customizationOptionService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy tùy chọn với id: " + id));
        }

        CustomizationOptionDTO result = customizationOptionService.save(customizationOptionDTO);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật tùy chọn thành công"));
    }

    @DeleteMapping("/customization-options/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomizationOption(@PathVariable("id") Integer id) {
        if (customizationOptionService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy tùy chọn với id: " + id));
        }
        customizationOptionService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa tùy chọn thành công"));
    }
}
