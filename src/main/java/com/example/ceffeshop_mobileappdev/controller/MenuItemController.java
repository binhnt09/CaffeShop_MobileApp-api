package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.entity.CustomizationGroup;
import com.example.ceffeshop_mobileappdev.entity.MenuItem;
import com.example.ceffeshop_mobileappdev.repository.BranchMenuItemRepository;
import com.example.ceffeshop_mobileappdev.repository.CustomizationOptionRepository;
import com.example.ceffeshop_mobileappdev.repository.MenuItemCustomizationRepository;
import com.example.ceffeshop_mobileappdev.service.MenuItemService;
import com.example.ceffeshop_mobileappdev.service.MenuItemQueryService;
import com.example.ceffeshop_mobileappdev.service.criteria.MenuItemCriteria;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDTO;
import com.example.ceffeshop_mobileappdev.service.dto.MenuItemDetailDTO;
import com.example.ceffeshop_mobileappdev.service.mapper.MenuItemMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final MenuItemQueryService menuItemQueryService;
    private final BranchMenuItemRepository branchMenuItemRepository;
    private final MenuItemCustomizationRepository menuItemCustomizationRepository;
    private final CustomizationOptionRepository customizationOptionRepository;
    private final MenuItemMapper menuItemMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getAllMenuItems(MenuItemCriteria criteria) {
        List<MenuItemDTO> items = menuItemQueryService.findByCriteria(criteria);
        return ResponseEntity.ok(ApiResponse.success(items, "Lấy danh sách món thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDTO>> getMenuItem(@PathVariable("id") Integer id) {
        return menuItemService.findOne(id)
                .map(itemDTO -> ResponseEntity.ok(ApiResponse.success(itemDTO, "Lấy thông tin món thành công")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Không tìm thấy món với id: " + id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MenuItemDTO>> createMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        if (menuItemDTO.getId() != null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Một món mới không được phép có ID trước"));
        }
        MenuItemDTO result = menuItemService.save(menuItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Tạo món mới thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDTO>> updateMenuItem(
            @PathVariable("id") Integer id,
            @Valid @RequestBody MenuItemDTO menuItemDTO
    ) {
        if (menuItemDTO.getId() == null) {
            menuItemDTO.setId(id);
        } else if (!menuItemDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID trong URL và Request Body không khớp"));
        }

        if (menuItemService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy món với id: " + id));
        }

        MenuItemDTO result = menuItemService.save(menuItemDTO);
        return ResponseEntity.ok(ApiResponse.success(result, "Cập nhật món thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenuItem(@PathVariable("id") Integer id) {
        if (menuItemService.findOne(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Không tìm thấy món với id: " + id));
        }
        menuItemService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa món thành công"));
    }

    // --- Endpoint: Lấy menu theo chi nhánh ---
    @GetMapping({"/branch/{branchId}", "/by-branch/{branchId}"})
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getMenuByBranch(@PathVariable Integer branchId) {
        List<MenuItem> items = branchMenuItemRepository.findAvailableMenuItemsByBranchId(branchId);
        if (items == null || items.isEmpty()) {
            // Fallback: Lấy toàn bộ món nếu chưa gán riêng cho chi nhánh
            List<MenuItemDTO> allItems = menuItemQueryService.findByCriteria(new MenuItemCriteria());
            return ResponseEntity.ok(ApiResponse.success(allItems, "Lấy menu mặc định cho chi nhánh thành công"));
        }
        List<MenuItemDTO> dtos = items.stream().map(menuItemMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success(dtos, "Lấy menu theo chi nhánh thành công"));
    }

    // --- Endpoint: Chi tiết 1 món + customizations ---
    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<MenuItemDetailDTO>> getMenuItemDetail(@PathVariable Integer id) {
        return menuItemService.findOne(id)
            .map(itemDTO -> {
                MenuItemDetailDTO detail = new MenuItemDetailDTO();
                detail.setId(itemDTO.getId());
                detail.setName(itemDTO.getName());
                detail.setDescription(itemDTO.getDescription());
                detail.setBasePrice(itemDTO.getBasePrice());
                detail.setImageUrl(itemDTO.getImageUrl());
                detail.setStatus(itemDTO.getStatus());
                detail.setCategoryId(itemDTO.getCategoryId());
                detail.setCategoryName(itemDTO.getCategoryName());

                // Load customization groups
                List<CustomizationGroup> groups = menuItemCustomizationRepository.findGroupsByMenuItemId(id);
                List<MenuItemDetailDTO.CustomizationGroupDetailDTO> groupDTOs = groups.stream().map(group -> {
                    MenuItemDetailDTO.CustomizationGroupDetailDTO gDTO = new MenuItemDetailDTO.CustomizationGroupDetailDTO();
                    gDTO.setGroupId(group.getId());
                    gDTO.setGroupName(group.getGroupName());
                    gDTO.setMinSelect(group.getMinSelect());
                    gDTO.setMaxSelect(group.getMaxSelect());

                    // Load options for each group
                    List<MenuItemDetailDTO.CustomizationOptionDetailDTO> optionDTOs =
                        customizationOptionRepository.findByGroupID(group).stream()
                            .filter(opt -> Boolean.TRUE.equals(opt.getIsAvailable()))
                            .map(opt -> {
                                MenuItemDetailDTO.CustomizationOptionDetailDTO oDTO = new MenuItemDetailDTO.CustomizationOptionDetailDTO();
                                oDTO.setOptionId(opt.getId());
                                oDTO.setOptionName(opt.getOptionName());
                                oDTO.setExtraPrice(opt.getExtraPrice());
                                oDTO.setIsAvailable(opt.getIsAvailable());
                                return oDTO;
                            }).toList();
                    gDTO.setOptions(optionDTOs);
                    return gDTO;
                }).toList();

                detail.setCustomizationGroups(groupDTOs);
                return ResponseEntity.ok(ApiResponse.success(detail, "Lấy chi tiết món thành công"));
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy món với id: " + id)));
    }
}
