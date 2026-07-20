package com.example.ceffeshop_mobileappdev.controller;

import com.example.ceffeshop_mobileappdev.entity.Customer;
import com.example.ceffeshop_mobileappdev.entity.LoyaltyTransaction;
import com.example.ceffeshop_mobileappdev.repository.CustomerRepository;
import com.example.ceffeshop_mobileappdev.repository.LoyaltyTransactionRepository;
import com.example.ceffeshop_mobileappdev.security.SecurityUtils;
import com.example.ceffeshop_mobileappdev.service.dto.ApiResponse;
import com.example.ceffeshop_mobileappdev.service.dto.LoyaltyInfoDTO;
import com.example.ceffeshop_mobileappdev.service.dto.LoyaltyTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loyalty")
@RequiredArgsConstructor
public class LoyaltyController {

    private final CustomerRepository customerRepository;
    private final LoyaltyTransactionRepository loyaltyTransactionRepository;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<LoyaltyInfoDTO>> getMyLoyalty() {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
        Customer customer = customerRepository.findByUserID_Id(userId.intValue())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin khách hàng cho user id: " + userId));

        int points = customer.getLoyaltyPoints();
        String tier = customer.getMembershipTier();

        LoyaltyInfoDTO dto = new LoyaltyInfoDTO();
        dto.setCustomerId(customer.getId());
        dto.setLoyaltyPoints(points);
        dto.setMembershipTier(tier);

        // Tier thresholds: Bronze (< 500), Silver (500-1999), Gold (>= 2000)
        if (points < 500) {
            dto.setNextTierName("Silver");
            dto.setPointsToNextTier(500 - points);
            dto.setTierProgress(points / 500.0);
        } else if (points < 2000) {
            dto.setNextTierName("Gold");
            dto.setPointsToNextTier(2000 - points);
            dto.setTierProgress((points - 500) / 1500.0);
        } else {
            dto.setNextTierName("Max Tier");
            dto.setPointsToNextTier(0);
            dto.setTierProgress(1.0);
        }

        return ResponseEntity.ok(ApiResponse.success(dto, "Lấy thông tin thành viên thành công"));
    }

    @GetMapping({"/transactions", "/my-transactions"})
    public ResponseEntity<ApiResponse<List<LoyaltyTransactionDTO>>> getTransactions() {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
        Customer customer = customerRepository.findByUserID_Id(userId.intValue())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin khách hàng"));

        List<LoyaltyTransaction> txs = loyaltyTransactionRepository
            .findByCustomerID_Id(customer.getId(), Sort.by(Sort.Direction.DESC, "createdAt"));

        List<LoyaltyTransactionDTO> dtos = txs.stream().map(tx -> {
            LoyaltyTransactionDTO d = new LoyaltyTransactionDTO();
            d.setId(tx.getId());
            d.setOrderId(tx.getOrderID().getId());
            d.setPointsEarned(tx.getPointsEarned());
            d.setPointsRedeemed(tx.getPointsRedeemed());
            d.setTransactionType(tx.getTransactionType());
            d.setCreatedAt(tx.getCreatedAt());
            return d;
        }).toList();

        return ResponseEntity.ok(ApiResponse.success(dtos, "Lấy lịch sử giao dịch điểm thành công"));
    }

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse<Map<String, Object>>> redeemPoints(@RequestBody Map<String, Integer> body) {
        Integer pointsToRedeem = body.get("points");
        if (pointsToRedeem == null || pointsToRedeem <= 0) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Số điểm đổi không hợp lệ"));
        }
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
        Customer customer = customerRepository.findByUserID_Id(userId.intValue())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin khách hàng"));

        if (customer.getLoyaltyPoints() < pointsToRedeem) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Không đủ điểm tích lũy để đổi"));
        }

        // Trừ điểm
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() - pointsToRedeem);
        customerRepository.save(customer);

        double redeemedValue = pointsToRedeem * 100.0; // 1 điểm = 100đ
        Map<String, Object> result = new HashMap<>();
        result.put("remainingPoints", customer.getLoyaltyPoints());
        result.put("redeemedValue", redeemedValue);
        result.put("message", "Đổi thành công " + pointsToRedeem + " điểm = " + (long)redeemedValue + "đ");

        return ResponseEntity.ok(ApiResponse.success(result, "Đổi điểm thành công"));
    }
}
